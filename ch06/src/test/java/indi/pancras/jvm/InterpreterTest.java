package indi.pancras.jvm;

import org.junit.jupiter.api.Test;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classpath.Classpath;

class InterpreterTest {

    @Test
    void testExecute() {
        Classpath classpath = Classpath.parse("", InterpreterTest.class.getClassLoader().getResource("").getPath());
        byte[] bytes = classpath.readClass("indi.pancras.jvm.testclass.GaussTest");
        ClassReader reader = new ClassReader(bytes);
        ClassFile classFile = reader.parseClassFile();
        try {
            Interpreter.execute(classFile.getMainMethod());
        } catch (RuntimeException ignored) {
        }
    }
}