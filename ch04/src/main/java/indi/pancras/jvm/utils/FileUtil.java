package indi.pancras.jvm.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileUtil {
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    public static boolean dirExists(String path) {
        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    public static String absPath(String path) {
        File file = new File(path);
        return file.getAbsolutePath();
    }

    public static String join(String path, String subpath) {
        File file = new File(path, subpath);
        return file.getPath();
    }

    public static List<String> list(String path) {
        File file = new File(path);
        String[] files = file.list();
        if (files == null) {
            return new ArrayList<>(0);
        }
        return Arrays.asList(files);
    }
}
