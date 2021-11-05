package indi.pancras.jvm.classpath;

import java.io.File;

import indi.pancras.jvm.classpath.impl.WildcardEntry;
import indi.pancras.jvm.utils.FileUtil;
import indi.pancras.jvm.utils.SystemUtil;


public class Classpath {
    private Entry bootClasspath;

    private Entry extClasspath;

    private Entry userClasspath;

    public static Classpath parse(String jreOption, String classpathOption) {
        Classpath classpath = new Classpath();
        classpath.parseBootAndExtClasspath(jreOption);
        classpath.parseUserClasspath(classpathOption);
        return classpath;
    }

    public byte[] readClass(String className) {
        // 将形如java.lang.String的类全限定名转换为java/lang/String.class
        className = className.replace(".", "/");
        className = className + ".class";
        byte[] data;
        data = bootClasspath.readClass(className);
        if (data != null) {
            return data;
        }
        data = extClasspath.readClass(className);
        if (data != null) {
            return data;
        }
        data = userClasspath.readClass(className);
        if (data != null) {
            return data;
        }
        throw new RuntimeException("java.lang.NoClassDefFoundError: " + className);
    }

    private void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        if (jreDir.isEmpty()) {
            throw new RuntimeException("Can not find jre dir!");
        }
        String jreLibPath = new File(jreDir, "lib/*").getAbsolutePath();
        bootClasspath = new WildcardEntry(jreLibPath);
        String jreLibExtPath = new File(jreDir, "lib/ext/*").getAbsolutePath();
        extClasspath = new WildcardEntry(jreLibExtPath);
    }

    private void parseUserClasspath(String classpathOption) {
        if (classpathOption.isEmpty()) {
            classpathOption = "";
        }
        userClasspath = EntryFactory.createEntry(classpathOption);
    }

    private String getJreDir(String jreOption) {
        if (FileUtil.dirExists(jreOption)) {
            return jreOption;
        } else if (FileUtil.dirExists("./jre")) {
            return "./jre";
        }
        String javaHome = SystemUtil.getEnv("JAVA_HOME");
        if (javaHome != null) {
            return new File(javaHome, "/jre").getAbsolutePath();
        }
        return "";
    }
}
