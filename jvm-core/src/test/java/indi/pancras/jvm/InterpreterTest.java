package indi.pancras.jvm;

import org.junit.jupiter.api.Test;

import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;

class InterpreterTest {
    Classpath classpath = Classpath.parse("", InterpreterTest.class.getClassLoader().getResource("").getPath());
    JClassLoader classLoader = new JClassLoader(classpath);

    @Test
    void testString() {
        String className = "indi.pancras.jvm.testclass.StringTest";
        excuteMain(className, false);
    }

    @Test
    void testMultiarray() {
        String className = "indi.pancras.jvm.testclass.MultiarrayTest";
        excuteMain(className, false);
    }

    @Test
    void testBubbleSort() {
        String className = "indi.pancras.jvm.testclass.BubbleSortTest";
        excuteMain(className, false);
    }

    @Test
    void testInvoke() {
        String className = "indi.pancras.jvm.testclass.InvokeDemo";
        excuteMain(className, true);
    }

    @Test
    void testFibonacci() {
        String className = "indi.pancras.jvm.testclass.FibonacciTest";
        excuteMain(className, false);
    }

    @Test
    void testGaussSum() {
        String className = "indi.pancras.jvm.testclass.GaussSum";
        excuteMain(className, true);
    }

    @Test
    void testStaticField() {
        String className = "indi.pancras.jvm.testclass.StaticField";
        JClass clazz = classLoader.loadClass(className);
        for (Field field : clazz.getFields()) {
            System.out.println(field.getFieldName());
        }
    }

    @Test
    void testMyObject() {
        String className = "indi.pancras.jvm.testclass.MyObject";
        excuteMain(className, true);
    }

    private void excuteMain(String className, boolean logInst) {
        JClass mainClass = classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        try {
            Interpreter.interpret(mainMethod, logInst);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}