package indi.pancras.jvm.rtda;

import org.junit.jupiter.api.Test;

import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.heap.JClass;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JClassLoaderTest {

    @Test
    void testLoadClass() {
        Classpath classpath = Classpath.parse("", JClassLoaderTest.class.getClassLoader().getResource("").getPath());
        JClassLoader loader = new JClassLoader(classpath);
        JClass clazz = loader.loadClass("indi.pancras.jvm.testclass.StaticFieldTest");
        assertNotNull(clazz);
    }
}