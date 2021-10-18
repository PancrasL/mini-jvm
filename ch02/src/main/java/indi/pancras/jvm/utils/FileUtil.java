package indi.pancras.jvm.utils;

import java.io.File;

/**
 * @author PancrasL
 */
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

    public static String[] list(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return file.list();
        }
        return null;
    }
}
