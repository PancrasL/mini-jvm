package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JClass {
    private ClassFile classFile;
    private int accessFlags;
    private String name;
    private String superClassName;
    private JClass superClass;
    private List<String> interfaceNames = new ArrayList<>();
    private List<JClass> interfaces = new ArrayList<>();
    private List<Field> fields = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();
    private int instanceSlotCount;
    private int staticSlotCount;

    private ClassLoader classLoader;
    private RuntimeConstantPool constantPool;

    public JClass(ClassFile classFile) {
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
