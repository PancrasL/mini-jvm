package indi.pancras.jvm.classpath.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.classpath.EntryFactory;

/**
 * @author PancrasL
 */
public class CompositeEntry implements Entry {
    private final List<Entry> entries = new ArrayList<>();

    public CompositeEntry(String pathList) {
        for (String path : pathList.split(File.pathSeparator)) {
            entries.add(EntryFactory.createEntry(path));
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
