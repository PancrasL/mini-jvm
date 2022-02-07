package indi.pancras.jvm;

import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;


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
        JClassLoader classLoader = new JClassLoader(classpath);
        JClass mainClass = classLoader.loadClass(cmd.getMainClass());
        Method mainMethod = mainClass.getMainMethod();

        Interpreter.interpret(mainMethod, true);
    }
}
