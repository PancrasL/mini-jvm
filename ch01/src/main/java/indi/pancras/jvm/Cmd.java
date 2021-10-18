package indi.pancras.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * @author PancrasL
 */

@Getter
@ToString
public class Cmd {
    @Parameter(names = {"-?", "-h", "--help"}, description = "print help info", help = true)
    private boolean helpFLag = false;

    @Parameter(names = {"-v", "--version"}, description = "print version info")
    private boolean versionFlag = false;

    @Parameter(names = {"-cp", "--classpath"}, description = "classpath option")
    private String classpath;

    @Parameter(description = "main class and args")
    private List<String> mainClassAndArgs;

    public String getMainClass() {
        if (mainClassAndArgs == null || mainClassAndArgs.isEmpty()) {
            return null;
        }
        return mainClassAndArgs.get(0);
    }

    public List<String> getAppArgs() {
        if (mainClassAndArgs == null || mainClassAndArgs.size() <= 1) {
            return null;
        }
        return mainClassAndArgs.subList(1, mainClassAndArgs.size());
    }

    public static Cmd parseCmd(String[] args) {
        Cmd cmd = new Cmd();
        JCommander.newBuilder()
                .addObject(cmd)
                .build()
                .parse(args);
        return cmd;
    }
}
