package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.base.AccessFlag;
import indi.pancras.jvm.rtda.base.Slot;
import lombok.Getter;
import lombok.Setter;

@Getter
public class JClass {
    /**
     * 通过ClassFile获取
     */
    private final ClassFile classFile;
    private final int accessFlags;
    private final String name;
    private final String superClassName;
    private final List<String> interfaceNames;
    private final List<Field> fields;
    private final List<Method> methods;
    private final RuntimeConstantPool constantPool;

    /**
     * 需要外部注入
     */
    @Setter
    private JClassLoader classLoader;
    @Setter
    private JClass superClass;
    @Setter
    private List<JClass> interfaces;
    @Setter
    private int instanceSlotCount;
    @Setter
    private int staticSlotCount;
    @Setter
    private List<Slot> staticSlots;

    public JClass(ClassFile classFile) {
        this.classFile = classFile;
        this.accessFlags = classFile.getAccessFlag();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();

        // 属性信息
        fields = new ArrayList<>();
        FieldInfo[] fieldInfos = classFile.getFields();
        for (FieldInfo info : fieldInfos) {
            fields.add(new Field(this, info));
        }
        // 方法信息
        methods = new ArrayList<>();
        MethodInfo[] methodsInfos = classFile.getMethods();
        for (MethodInfo info : methodsInfos) {
            methods.add(new Method(this, info));
        }
        this.constantPool = new RuntimeConstantPool(this, classFile.getConstantPool());
    }

    public boolean canBeAccessedBy(JClass other) {
        // 类是共有的 or 类在同一个包下
        return isPublic() || (getPackageName().equals(other.getPackageName()));
    }

    public String getPackageName() {
        int i = name.lastIndexOf('/');
        return i == -1 ? "" : name.substring(0, i);
    }

    public boolean isSubClassOf(JClass other) {
        for (JClass c = this.superClass; c != null; c = c.superClass) {
            if (c == other) {
                return true;
            }
        }
        return false;
    }

    public boolean isPublic() {
        return (accessFlags & AccessFlag.ACC_PUBLIC) != 0;
    }

    public boolean isFinal() {
        return (accessFlags & AccessFlag.ACC_FINAL) != 0;
    }

    public boolean isSuper() {
        return (accessFlags & AccessFlag.ACC_SUPER) != 0;
    }

    public boolean isInterface() {
        return (accessFlags & AccessFlag.ACC_INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (accessFlags & AccessFlag.ACC_ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & AccessFlag.ACC_SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (accessFlags & AccessFlag.ACC_ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (accessFlags & AccessFlag.ACC_ENUM) != 0;
    }

}
