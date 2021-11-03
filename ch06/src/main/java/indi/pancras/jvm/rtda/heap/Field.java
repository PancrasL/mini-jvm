package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.rtda.base.AccessFlag;
import indi.pancras.jvm.rtda.base.DescriptorFlag;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Field {
    private final short accessFlags;
    private final String name;
    private final String descriptor;

    private int constValueIndex;
    @Setter
    private int slotId;

    public Field(JClass jClass, FieldInfo info) {
        this.accessFlags = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
        this.constValueIndex = info.getConstValueIndex();
    }

    public boolean isPublic() {
        return (accessFlags & AccessFlag.ACC_PUBLIC) != 0;
    }

    public boolean isPrivate() {
        return (accessFlags & AccessFlag.ACC_PRIVATE) != 0;
    }

    public boolean isProtected() {
        return (accessFlags & AccessFlag.ACC_PROTECTED) != 0;
    }

    public boolean isStatic() {
        return (accessFlags & AccessFlag.ACC_STATIC) != 0;
    }

    public boolean isFinal() {
        return (accessFlags & AccessFlag.ACC_FINAL) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & AccessFlag.ACC_SYNTHETIC) != 0;
    }

    public boolean isVolatile() {
        return (accessFlags & AccessFlag.ACC_VOLATILE) != 0;
    }

    public boolean isTransient() {
        return (accessFlags & AccessFlag.ACC_TRANSIENT) != 0;
    }

    public boolean isEnum() {
        return (accessFlags & AccessFlag.ACC_ENUM) != 0;
    }

    public boolean isLongOrDouble() {
        return descriptor.equals(DescriptorFlag.LONG_FLAG) || descriptor.equals(DescriptorFlag.DOUBLE_FLAG);
    }
}
