package indi.pancras.jvm;

import java.io.IOException;

import indi.pancras.jvm.classpath.Classpath;

/**
 * @author PancrasL
 */
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
        System.out.println(new String(bytes));
    }
}
