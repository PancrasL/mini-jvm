package indi.pancras.jvm.rtda.heap;

import java.util.List;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
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
    private RuntimeConstantPool constantPool;

    public Class(ClassFile classFile) {
        this.classFile = classFile;
        this.accessFlags = classFile.getAccessFlag();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        // 接口信息

        // 属性信息
        FieldInfo[] fieldInfos = classFile.getFields();
        for (FieldInfo info : fieldInfos) {
            fields.add(new Field(this, info));
        }
        // 方法信息
        MethodInfo[] methodsInfos = classFile.getMethods();
        for (MethodInfo info : methodsInfos) {
            methods.add(new Method(this, info));
        }
        this.constantPool = new RuntimeConstantPool(classFile.getConstantPool());
    }
}
