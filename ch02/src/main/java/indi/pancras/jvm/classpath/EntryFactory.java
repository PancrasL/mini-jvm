package indi.pancras.jvm.classpath;

import java.io.File;

import indi.pancras.jvm.classpath.impl.CompositeEntry;
import indi.pancras.jvm.classpath.impl.DirEntry;
import indi.pancras.jvm.classpath.impl.WildcardEntry;
import indi.pancras.jvm.classpath.impl.ZipEntry;

/**
 * @author PancrasL
 */
public class EntryFactory {
    public static Entry createEntry(String path) {
        Entry entry;
        if (path.contains(File.pathSeparator)) {
            entry = new CompositeEntry(path);
        } else if (path.endsWith("*")) {
            entry = new WildcardEntry(path);
        } else if (path.endsWith(".jar") || path.endsWith(".JAR") ||
                path.endsWith(".zip") || path.endsWith(".ZIP")) {
            entry = new ZipEntry(path);
        } else {
            entry = new DirEntry(path);
        }
        return entry;
    }
}
