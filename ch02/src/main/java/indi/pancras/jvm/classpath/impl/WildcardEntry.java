package indi.pancras.jvm.classpath.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.utils.FileUtil;

/**
 * @author PancrasL
 */
public class WildcardEntry implements Entry {
    private List<Entry> entries = new ArrayList<>();

    public WildcardEntry(String path) {
        // remove *
        path = path.substring(0, path.length() - 1);

        String[] list = FileUtil.list(path);
        if (list != null) {
            for (String file : list) {
                if (file.endsWith(".jar") || file.endsWith(".JAR")) {
                    entries.add(new ZipEntry(FileUtil.join(path, file)));
                }
            }
        }
    }

    @Override
    public byte[] readClass(String classFile) throws IOException {
        for (Entry entry : entries) {
            try {
                return entry.readClass(classFile);
            } catch (FileNotFoundException ignored) {
            }
        }
        throw new FileNotFoundException("Class not found: " + classFile);
    }
}
