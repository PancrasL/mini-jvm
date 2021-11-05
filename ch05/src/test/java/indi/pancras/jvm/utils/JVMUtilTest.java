package indi.pancras.jvm.utils;

import org.junit.jupiter.api.Test;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classpath.Classpath;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JVMUtilTest {

    @Test
    void testPrintClassInfo() {
        Classpath classpath = Classpath.parse("", "");
        byte[] bytes = classpath.readClass("java.lang.String");
        assertNotNull(bytes);
        ClassReader reader = new ClassReader(bytes);
        ClassFile classFile = reader.parseClassFile();
        JVMUtil.printClassInfo(classFile);
    }
}