package indi.pancras.jvm.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class IOUtilTest {
    @Test
    void readFile() {
        // 相对于项目根路径来说
        assertNull(IOUtil.readFile("not_exist.txt"));
        assertNotNull(IOUtil.readFile("pom.xml"));
    }
}