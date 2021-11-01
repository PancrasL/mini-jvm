package indi.pancras.jvm;

import java.io.IOException;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import indi.pancras.jvm.classpath.Classpath;


public class MiniJvmBootstrap {
    public static void main(String[] args) throws IOException {
        Cmd cmd = Cmd.parseCmd(args);
        if (cmd.isHelpFlag() || cmd.getMainClass() == null) {
            System.out.println("Usage: <main class> [-options] class [args...]");
        } else if (cmd.isVersionFlag()) {
            System.out.println("Version 0.1");
        } else {
            startJVM(cmd);
        }
    }

    private static void startJVM(Cmd cmd) {
        Classpath classpath = Classpath.parse(cmd.getJreOption(), cmd.getClasspath());
        byte[] bytes = classpath.readClass(cmd.getMainClass());
        printClassInfo(new ClassFile(bytes));
        System.out.println(new String(bytes));
    }

    private static void printClassInfo(ClassFile classFile) {
        System.out.println("version: " + classFile.getMajorVersion() + "." + classFile.getMinorVersion());

        ConstantPool constantPool = classFile.getConstantPool();
        BaseConstantInfo[] constantInfos = constantPool.getConstantInfos();
        System.out.println("constants count: " + constantPool.getPoolSize());
        for (int i = 1; i < constantPool.getPoolSize(); i++) {
            if (constantInfos[i] != null) {
                System.out.println(i + ": " + constantInfos[i]);
            }
        }

        System.out.format("access flags: 0x%x\n", classFile.getAccessFlag());
        System.out.println("this class: " + constantPool.getUTF8(classFile.getThisClassIndex()));
        System.out.println("super class: " + constantPool.getUTF8(classFile.getSuperClassIndex()));
        System.out.println("interfaces: " + classFile.getInterfaceIndexes().length);
        FieldInfo[] fields = classFile.getFields();
        System.out.println("fields count: " + fields.length);
        for (FieldInfo field : fields) {
            System.out.format("  %s\n", constantPool.getUTF8(field.getNameIndex()));
        }

        MethodInfo[] methods = classFile.getMethods();
        System.out.println("methods count: " + methods.length);
        for (MethodInfo m : methods) {
            System.out.format("  %s\n", constantPool.getUTF8(m.getNameIndex()));
        }
    }
}
