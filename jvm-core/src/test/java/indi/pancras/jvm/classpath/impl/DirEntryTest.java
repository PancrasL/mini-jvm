package indi.pancras.jvm.classpath.impl;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class DirEntryTest {
    @Test
    void testReadClass() {
        String resourcePath = Objects.requireNonNull(DirEntry.class.getClassLoader().getResource("")).getPath();
        DirEntry dirEntry = new DirEntry(resourcePath);
        byte[] bytes = dirEntry.readClass("indi/pancras/jvm/classpath/impl/DirEntryTest.class");
        assertNotNull(bytes);

        assertNull(dirEntry.readClass("not_exist_file.txt"));
    }
}