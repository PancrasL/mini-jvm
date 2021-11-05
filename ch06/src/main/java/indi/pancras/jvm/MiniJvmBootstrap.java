package indi.pancras.jvm;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classpath.Classpath;


public class MiniJvmBootstrap {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parseCmd(args);
        if (cmd.isHelpFlag() || cmd.getMainClass().isEmpty()) {
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
        ClassReader reader = new ClassReader(bytes);
        ClassFile classFile = reader.parseClassFile();
        Interpreter.execute(classFile.getMainMethod());
    }
}
