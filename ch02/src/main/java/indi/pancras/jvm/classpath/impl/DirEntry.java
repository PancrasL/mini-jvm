package indi.pancras.jvm.classpath.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.utils.FileUtil;
import indi.pancras.jvm.utils.IOUtil;

/**
 * @author PancrasL
 */
public class DirEntry implements Entry {
    private String absPath;

    public DirEntry(String path) {
        absPath = FileUtil.absPath(path);
    }

    @Override
    public byte[] readClass(String classFile) throws IOException {
        return IOUtil.readFile(FileUtil.join(absPath, classFile));
    }
}
