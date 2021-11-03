package indi.pancras.jvm.rtda;

import org.junit.jupiter.api.Test;

import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.heap.JClass;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JClassLoaderTest {

    @Test
    void testLoadClass() {
        Classpath classpath = Classpath.parse("", "");
        JClassLoader jClassLoader = new JClassLoader(classpath);
        JClass jClass = jClassLoader.loadClass("java.lang.String");
        assertNotNull(jClass);
    }
}