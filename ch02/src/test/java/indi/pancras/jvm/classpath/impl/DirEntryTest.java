package indi.pancras.jvm.classpath.impl;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author PancrasL
 */
class DirEntryTest {
    @Test
    void testReadClass() throws IOException {
        String resourcePath = Objects.requireNonNull(DirEntry.class.getClassLoader().getResource("")).getPath();
        DirEntry dirEntry = new DirEntry(resourcePath);
        byte[] bytes = dirEntry.readClass("test.txt");
        assertArrayEquals("hello world!".getBytes(), bytes);

        assertThrows(FileNotFoundException.class, ()-> dirEntry.readClass("not_exist_file.txt"));
    }
}