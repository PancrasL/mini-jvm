package indi.pancras.jvm.classpath.impl;

import java.io.IOException;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.utils.FileUtil;


public class DirEntry implements Entry {
    private final String absPath;

    public DirEntry(String path) {
        absPath = FileUtil.absPath(path);
    }

    /**
     * @param classFile 类文件的路径，如java/lang/String.class
     * @return 类文件数据
     */
    @Override
    public byte[] readClass(String classFile) {
        try {
            return FileUtil.readFile(FileUtil.join(absPath, classFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}