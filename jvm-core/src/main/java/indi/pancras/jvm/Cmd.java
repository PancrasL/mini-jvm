package indi.pancras.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * 使用： ① 指定jre目录： -jre D:\Programmes\Java\JavaSE8\jdk\jre java.lang.String ②
 * 指定classpath（从项目根目录的test文件夹下加载类）： -cp test DirEntry
 */
@Getter
@ToString
public class Cmd {
    @Parameter(names = {"-?", "-h", "--help"}, description = "print help info", help = true)
    private final boolean helpFlag = false;

    @Parameter(names = {"-v", "--version"}, description = "print version info")
    private final boolean versionFlag = false;

    @Parameter(names = {"-cp", "--classpath"}, description = "classpath option")
    private final String classpath = "";

    @Parameter(names = {"-jre", "--jreOption"}, description = "jre path option")
    private final String jreOption = "";

    @Parameter(description = "main class and args")
    private final List<String> mainClassAndArgs = new ArrayList<>();

    public static Cmd parseCmd(String[] args) {
        Cmd cmd = new Cmd();
        JCommander.newBuilder()
                .addObject(cmd)
                .build()
                .parse(args);
        return cmd;
    }

    public String getMainClass() {
        if (mainClassAndArgs.isEmpty()) {
            return "";
        }
        return mainClassAndArgs.get(0);
    }

    public List<String> getAppArgs() {
        if (mainClassAndArgs.size() <= 1) {
            return new ArrayList<>();
        }
        return mainClassAndArgs.subList(1, mainClassAndArgs.size());
    }
}
