package indi.pancras.jvm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class IOUtil {
    public static byte[] readFile(String filename) {
        File file = new File(filename);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }
}
