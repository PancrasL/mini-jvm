package indi.pancras.jvm.classpath.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.classpath.EntryFactory;


public class CompositeEntry implements Entry {
    private final List<Entry> entries = new ArrayList<>();

    public CompositeEntry(String pathList) {
        for (String path : pathList.split(File.pathSeparator)) {
            entries.add(EntryFactory.createEntry(path));
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
