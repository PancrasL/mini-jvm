package indi.pancras.jvm.rtda;

import java.util.HashMap;
import java.util.Map;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classpath.Classpath;
import indi.pancras.jvm.rtda.heap.JClass;

public class JClassLoader {
    private final Classpath classpath;
    /**
     * save loaded class，可以视为方法区的具体实现 key是类的全限定名，形如java.lang.String
     */
    private final Map<String, JClass> classMap;

    public JClassLoader(Classpath classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
    }

    public JClass loadClass(String className) {
        if (classMap.containsKey(className)) {
            return classMap.get(className);
        }
        JClass jClass = loadNonArrayClass(className);
        classMap.put(className, jClass);
        return jClass;
    }

    private JClass loadNonArrayClass(String className) {
        // 读取class文件
        byte[] bytes = classpath.readClass(className);
        // 创建class文件读取器
        ClassReader reader = new ClassReader(bytes);
        // 解析class文件
        ClassFile classFile = reader.parseClassFile();
        // 将解析后的class文件转换为类对象
        return new JClass(classFile);
    }
}
