package indi.pancras.jvm.rtda.heap;

import java.util.List;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Class {
    private ClassFile classFile;
    private int accessFlags;
    private String name;
    private String superClassName;
    private Class superClass;
    private List<String> interfaceNames;
    private List<Class> interfaces;
    private List<Field> fields;
    private List<Method> methods;
    private int instanceSlotCount;
    private int staticSlotCount;

    private ClassLoader classLoader;
    private ConstantPool constantPool;

    public Class(ClassFile classFile) {
        this.classFile = classFile;
        this.accessFlags = classFile.getAccessFlag();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();

        this.constantPool = classFile.getConstantPool();
    }
}
