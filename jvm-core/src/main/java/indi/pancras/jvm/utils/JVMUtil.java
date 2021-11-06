package indi.pancras.jvm.utils;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;

public class JVMUtil {
    public static void printClassInfo(ClassFile classFile) {
        System.out.println("version: " + classFile.getMajorVersion() + "." + classFile.getMinorVersion());

        ConstantPool constantPool = classFile.getConstantPool();
        BaseConstantInfo[] constantInfos = constantPool.getConstantInfos();
        System.out.println("constants size: " + constantPool.getPoolSize());
        System.out.println("constants count: " + constantPool.getPoolCount());
        for (int i = 1; i < constantPool.getPoolSize(); i++) {
            if (constantInfos[i] != null) {
                System.out.println(i + ": " + constantInfos[i]);
            }
        }

        System.out.format("access flags: 0x%x\n", classFile.getAccessFlag());
        System.out.println("this class: " + constantPool.getUtf8(classFile.getThisClassIndex()));
        System.out.println("super class: " + constantPool.getUtf8(classFile.getSuperClassIndex()));
        System.out.println("interfaces: " + classFile.getInterfaceIndexes().length);
        FieldInfo[] fields = classFile.getFields();
        System.out.println("fields count: " + fields.length);
        for (FieldInfo field : fields) {
            System.out.format("  %s\n", constantPool.getUtf8(field.getNameIndex()));
        }

        MethodInfo[] methods = classFile.getMethods();
        System.out.println("methods count: " + methods.length);
        for (MethodInfo m : methods) {
            System.out.format("  %s\n", constantPool.getUtf8(m.getNameIndex()));
        }
    }
}
