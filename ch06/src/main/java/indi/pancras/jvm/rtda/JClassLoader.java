package indi.pancras.jvm.rtda;

import indi.pancras.jvm.rtda.base.DescriptorFlag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.base.Slot;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;

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
    }

    /**
     * @param className 类的全限定名，形如java.lang.String或者java/lang/String
     * @return 类变量
     */
    public JClass loadClass(String className) {
        if (classMap.containsKey(className)) {
            return classMap.get(className);
        }
        JClass jClass = loadNonArrayClass(className);
        return jClass;
    }

    private JClass loadNonArrayClass(String className) {
        // 读取class文件
        byte[] bytes = readClass(className);
        // 加载
        JClass jClass = defineClass(bytes);
        // 链接
        if (className.contains("Static")) {
            int a = 1;
        }
        link(jClass);
        return jClass;
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
        JClass jClass = new JClass(classFile);
        jClass.setClassLoader(this);
        resolveSuperClass(jClass);
        resolveInterfaceClass(jClass);
        classMap.put(jClass.getName(), jClass);
        return jClass;
    }

    /**
     * 链接：包含类的验证、准备和解析
     *
     * @param jClass 需要进行链接的类变量
     */
    private void link(JClass jClass) {
        verify(jClass);
        prepare(jClass);
    }

    private void verify(JClass jClass) {
        // do verify
    }

    private void prepare(JClass jClass) {
        processInstanceFieldSlotId(jClass);
        processStaticFieldSlotId(jClass);
        allocAndInitStaticVars(jClass);
    }

    /**
     * 处理实例字段的编号和个数数
     *
     * @param jClass 待处理的类变量
     */
    private void processInstanceFieldSlotId(JClass jClass) {
        int slotId = 0;
        if (jClass.getSuperClass() != null) {
            slotId = jClass.getSuperClass().getInstanceSlotCount();
        }
        List<Field> fields = jClass.getFields();
        for (Field field : fields) {
            if (!field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        jClass.setInstanceSlotCount(slotId);
    }

    /**
     * 处理静态字段的编号和个数
     *
     * @param jClass 待处理的类变量
     */
    private void processStaticFieldSlotId(JClass jClass) {
        int slotId = 0;
        for (Field field : jClass.getFields()) {
            if (field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        jClass.setStaticSlotCount(slotId);
    }

    /**
     * 链接的准备阶段，为类变量分配空间，并给他们赋初始值
     *
     * @param jClass 待处理的类变量
     */
    private void allocAndInitStaticVars(JClass jClass) {
        List<Slot> staticSlots = new ArrayList<>(jClass.getStaticSlotCount());
        List<Field> fields = jClass.getFields();
        RuntimeConstantPool pool = jClass.getConstantPool();
        for (Field field : fields) {
            if (field.isStatic() && field.isFinal()) {
                int index = field.getConstValueIndex();
                int slotId = field.getSlotId();
                if (index > 0) {
                    switch (field.getDescriptor()) {
                        case DescriptorFlag.BOOLEAN_FLAG:
                        case DescriptorFlag.BYTE_FLAG:
                        case DescriptorFlag.CHAR_FLAG:
                        case DescriptorFlag.SHORT_FLAG:
                        case DescriptorFlag.INT_FLAG: {
                            int val = pool.getInt(index);
                            staticSlots.add(slotId, new Slot(val));
                        }
                        break;
                        case DescriptorFlag.FLOAT_FLAG: {
                            float val = pool.getFloat(index);
                            staticSlots.add(slotId, new Slot(Float.floatToIntBits(val)));
                        }
                        break;
                        case DescriptorFlag.LONG_FLAG: {
                            long val = pool.getLong(index);
                            staticSlots.add(slotId, new Slot((int) (val >> 32)));
                            staticSlots.add(slotId + 1, new Slot((int) (val)));
                        }
                        break;
                        case DescriptorFlag.DOUBLE_FLAG:
                            double val = pool.getDouble(index);
                            long bits = Double.doubleToLongBits(val);
                            staticSlots.add(slotId, new Slot((int) (bits >> 32)));
                            staticSlots.add(slotId + 1, new Slot((int) (bits)));
                            break;
                        case DescriptorFlag.OBJECT_FLAG:
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void resolveInterfaceClass(JClass jClass) {
        List<String> interfaceNames = jClass.getInterfaceNames();
        List<JClass> interfaces = new ArrayList<>(interfaceNames.size());
        for (String interfaceName : interfaceNames) {
            JClass c = jClass.getClassLoader().loadClass(interfaceName);
            interfaces.add(c);
        }
        jClass.setInterfaces(interfaces);
    }

    private void resolveSuperClass(JClass jClass) {
        if (!jClass.getSuperClassName().equals(OBJECT_CLASS_NAME)) {
            JClass c = jClass.getClassLoader().loadClass(jClass.getSuperClassName());
            jClass.setSuperClass(c);
        }
    }
}
