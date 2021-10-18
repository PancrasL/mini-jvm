package indi.pancras.jvm.classpath.impl;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import indi.pancras.jvm.classpath.Entry;
import indi.pancras.jvm.utils.FileUtil;

/**
 * @author PancrasL
 */
public class ZipEntry implements Entry {
    private final String absPath;

    public ZipEntry(String path) {
        absPath = FileUtil.absPath(path);
    }

    @Override
    public byte[] readClass(String classFile) {
        try (FileSystem zipFs = FileSystems.newFileSystem(Paths.get(absPath), null)) {
            return Files.readAllBytes(zipFs.getPath(classFile));
        } catch (IOException e) {
            return null;
        }
    }
}
