package indi.pancras.jvm;

/**
 * @author PancrasL
 */
public class MiniJvmBootstrap {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parseCmd(args);
        if (cmd.isHelpFLag() || cmd.getMainClass() == null) {
            System.out.println("Usage: <main class> [-options] class [args...]");
        } else if (cmd.isVersionFlag()) {
            System.out.println("Version 0.1");
        } else {
            startJVM(cmd);
        }
    }

    private static void startJVM(Cmd cmd) {
        System.out.printf("classpath:%s class:%s args:%s\n",
                cmd.getClasspath(), cmd.getMainClass(), cmd.getAppArgs());
    }
}
