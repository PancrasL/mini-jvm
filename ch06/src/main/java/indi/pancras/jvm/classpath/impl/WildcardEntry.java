package indi.pancras.jvm.classpath.impl;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.utils.FileUtil;


public class WildcardEntry implements Entry {
    private final List<Entry> entries = new ArrayList<>();

    public WildcardEntry(String path) {
        // remove *
        path = path.substring(0, path.length() - 1);

        List<String> fileList = FileUtil.list(path);
        for (String file : fileList) {
            if (file.endsWith(".jar") || file.endsWith(".JAR")) {
                entries.add(new ZipEntry(FileUtil.join(path, file)));
            }
        }
    }

    @Override
    public byte[] readClass(String classFile) {
        for (Entry entry : entries) {
            byte[] bytes = entry.readClass(classFile);
            if (bytes != null) {
                return bytes;
            }
        }
        return null;
    }
}
