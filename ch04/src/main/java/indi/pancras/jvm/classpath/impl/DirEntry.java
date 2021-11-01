package indi.pancras.jvm.classpath.impl;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.utils.FileUtil;
import indi.pancras.jvm.utils.IOUtil;


public class DirEntry implements Entry {
    private final String absPath;

    public DirEntry(String path) {
        absPath = FileUtil.absPath(path);
    }

    @Override
    public byte[] readClass(String classFile) {
        return IOUtil.readFile(FileUtil.join(absPath, classFile));
    }
}