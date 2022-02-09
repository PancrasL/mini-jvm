package indi.pancras.jvm.rtda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JClassNameHelper;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.heap.StringPool;
import indi.pancras.jvm.utils.SlotsUtil;

public class JClassLoader {
    private static final String OBJECT_CLASS_NAME = "java/lang/Object";
    private final Classpath classpath;
    /**
     * save loaded class，可以视为方法区的具体实现 key是类的全限定名，形如java.lang.String
     */
    private final Map<String, JClass> classMap;

    public JClassLoader(Classpath classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
        loadBasicClasses();
        loadPrimitiveClasses();
    }

    /**
     * 加载java.lang.Class类，及其父类java.lang.Object
     */
    private void loadBasicClasses() {
        // 将每一个加载到方法区中的类都和一个类对象关联
        JClass jlClassClass = loadClass("java.lang.Class");
        for (JClass clazz : classMap.values()) {
            if (clazz.getClazzObj() == null) {// 如果当前类没有关联类对象，则将该类关联到一个Class对象
                JObject clazzObj = jlClassClass.newInstance();
                clazzObj.setExtra(clazz);
                clazz.setClazzObj(new Reference(clazzObj));
            }
        }
    }

    /**
     * 加载void、int、short等基本数据类型的类
     */
    private void loadPrimitiveClasses() {
        for (String type : JClassNameHelper.primitiveTypes.keySet()) {
            loadPrimitiveClass(type);
        }
    }

    private void loadPrimitiveClass(String type) {
        JClass primitiveClass = new JClass();
        primitiveClass.setAccessFlags(AccessFlag.ACC_PUBLIC);
        primitiveClass.setClassName(type);
        primitiveClass.setClassLoader(this);
        primitiveClass.setInitStarted(true);
        JObject classObj = classMap.get("java/lang/Class").newInstance();
        classObj.setExtra(primitiveClass);
        primitiveClass.setClazzObj(new Reference(classObj));
        classMap.put(type, primitiveClass);
    }

    /**
     * @param className 类的全限定名，形如java.lang.String
     * @return 类变量
     */
    public JClass loadClass(String className) {
        className = className.replace('.', '/');
        if (classMap.containsKey(className)) {// already loaded
            return classMap.get(className);
        }
        JClass clazz;
        if (className.charAt(0) == '[') {// array class
            clazz = loadArrayClass(className);
        } else {
            clazz = loadNonArrayClass(className);// non array class
        }
        if (classMap.containsKey("java/lang/Class")) {// 为当前加载的类关联上类对象
            JClass jlClassClass = classMap.get("java/lang/Class");
            JObject classObj = jlClassClass.newInstance();
            classObj.setExtra(clazz);
            clazz.setClazzObj(new Reference(classObj));
        }
        return clazz;
    }

    private JClass loadNonArrayClass(String className) {
        // 读取class文件
        byte[] bytes = readClass(className);
        // 加载
        JClass clazz = defineClass(bytes);
        // 链接
        link(clazz);
        return clazz;
    }

    private JClass loadArrayClass(String className) {
        JClass clazz = new JClass();
        clazz.setAccessFlags(AccessFlag.ACC_PUBLIC);
        clazz.setClassName(className);
        clazz.setClassLoader(this);
        clazz.setSuperClassName("java.lang.Object");
        clazz.setSuperClass(loadClass("java.lang.Object"));
        clazz.setInterfaceNames(Arrays.asList(
                "java.lang.Clonable",
                "java.io.Serializable"
        ));
        clazz.setInterfaces(Arrays.asList(
                this.loadClass("java.lang.Cloneable"),
                this.loadClass("java.io.Serializable")
        ));
        clazz.setInitStarted(true);
        classMap.put(className, clazz);
        return clazz;
    }

    /**
     * 加载类文件
     *
     * @param className 类的全限定名
     * @return 类文件的二进制数据
     */
    private byte[] readClass(String className) {
        return classpath.readClass(className);
    }

    /**
     * 类加载，将类文件的二进制字节流转换为类变量
     *
     * @param data 类文件的二进制字节流
     * @return 类变量
     */
    private JClass defineClass(byte[] data) {
        ClassReader reader = new ClassReader(data);
        ClassFile classFile = reader.parseClassFile();
        JClass clazz = new JClass(classFile);
        clazz.setClassLoader(this);
        resolveSuperClass(clazz);
        resolveInterfaceClass(clazz);
        classMap.put(clazz.getClassName(), clazz);
        return clazz;
    }

    /**
     * 链接：包含类的验证、准备和解析
     *
     * @param clazz 需要进行链接的类变量
     */
    private void link(JClass clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void verify(JClass clazz) {
        // do verify
    }

    private void prepare(JClass clazz) {
        processInstanceFieldSlotId(clazz);
        processStaticFieldSlotId(clazz);
        allocAndInitStaticVars(clazz);
    }

    /**
     * 处理实例字段的编号和个数数
     *
     * @param clazz 待处理的类变量
     */
    private void processInstanceFieldSlotId(JClass clazz) {
        int slotId = 0;
        if (clazz.getSuperClass() != null) {
            slotId = clazz.getSuperClass().getInstanceSlotCount();
        }
        List<Field> fields = clazz.getFields();
        for (Field field : fields) {
            if (!field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.setInstanceSlotCount(slotId);
    }

    /**
     * 处理静态字段的编号和个数
     *
     * @param clazz 待处理的类变量
     */
    private void processStaticFieldSlotId(JClass clazz) {
        int slotId = 0;
        for (Field field : clazz.getFields()) {
            if (field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.setStaticSlotCount(slotId);
    }

    /**
     * 链接的准备阶段，为类变量分配空间，并给他们赋初始值
     *
     * @param clazz 待处理的类变量
     */
    private void allocAndInitStaticVars(JClass clazz) {
        clazz.setStaticFields(new Slot[clazz.getStaticSlotCount()]);
        List<Field> fields = clazz.getFields();
        RuntimeConstantPool pool = clazz.getConstantPool();
        for (Field field : fields) {
            if (field.isStatic() && field.isFinal()) {
                int index = field.getConstValueIndex();
                int slotId = field.getSlotId();
                Slot[] slots = clazz.getStaticFields();
                if (index > 0) {
                    switch (field.getDescriptor()) {
                        case "Z":
                        case "B":
                        case "C":
                        case "S":
                        case "I":
                            SlotsUtil.setInt(slots, slotId, pool.getInt(index));
                            break;
                        case "F":
                            SlotsUtil.setFloat(slots, slotId, pool.getFloat(index));
                            break;
                        case "J":
                            SlotsUtil.setLong(slots, slotId, pool.getLong(index));
                            break;
                        case "D":
                            SlotsUtil.setDouble(slots, slotId, pool.getDouble(index));
                            break;
                        case "Ljava/lang/String;":
                            Reference jStr = StringPool.getJString(clazz.getClassLoader(), pool.getString(index));
                            SlotsUtil.setRef(slots, slotId, jStr);
                            break;
                        case "L":
                        default:
                            throw new RuntimeException("Not implemented: " + field.getDescriptor());
                    }
                }
            }
        }
    }

    private void resolveInterfaceClass(JClass clazz) {
        List<String> interfaceNames = clazz.getInterfaceNames();
        List<JClass> interfaces = new ArrayList<>(interfaceNames.size());
        for (String interfaceName : interfaceNames) {
            JClass c = clazz.getClassLoader().loadClass(interfaceName);
            interfaces.add(c);
        }
        clazz.setInterfaces(interfaces);
    }

    private void resolveSuperClass(JClass clazz) {
        if (!clazz.getClassName().equals(OBJECT_CLASS_NAME)) {
            JClass c = clazz.getClassLoader().loadClass(clazz.getSuperClassName());
            clazz.setSuperClass(c);
        }
    }
}
