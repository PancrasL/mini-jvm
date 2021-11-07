package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.rtda.AccessFlag;
import indi.pancras.jvm.rtda.DescriptorFlag;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Field {

    private final JClass clazz;
    private final short accessFlags;
    private final String fieldName;
    private final String descriptor;
    private final int constValueIndex;

    @Setter
    private int slotId;

    public Field(JClass clazz, FieldInfo info) {
        this.clazz = clazz;
        this.accessFlags = info.getAccessFlags();
        this.fieldName = info.getName();
        this.descriptor = info.getDescriptor();
        this.constValueIndex = info.getConstValueIndex();
    }

    public boolean canBeAccessedBy(JClass other) {
        if (isPublic()) {
            return true;
        }
        if (isProtected()) {
            return clazz == other ||
                    clazz.getPackageName().equals(other.getPackageName()) ||
                    other.isSubClassOf(clazz);
        }
        if (isPrivate()) {
            return clazz == other;
        }
        return clazz.getPackageName().equals(other.getPackageName());
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
