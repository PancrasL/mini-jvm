package indi.pancras.jvm.rtda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.base.DescriptorFlag;
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
        JClass clazz = loadNonArrayClass(className);
        return clazz;
    }

    private JClass loadNonArrayClass(String className) {
        // 读取class文件
        byte[] bytes = readClass(className);
        // 加载
        JClass clazz = defineClass(bytes);
        // 链接
        if (className.contains("Static")) {
            int a = 1;
        }
        link(clazz);
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
        List<Slot> staticSlots = new ArrayList<>(clazz.getStaticSlotCount());
        List<Field> fields = clazz.getFields();
        RuntimeConstantPool pool = clazz.getConstantPool();
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
        clazz.setStaticFields(staticSlots);
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
        if (!clazz.getSuperClassName().equals(OBJECT_CLASS_NAME)) {
            JClass c = clazz.getClassLoader().loadClass(clazz.getSuperClassName());
            clazz.setSuperClass(c);
        }
    }
}
