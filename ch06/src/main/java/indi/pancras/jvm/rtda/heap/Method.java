package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.classfile.attribute.attrinfo.CodeAttr;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.rtda.base.AccessFlag;
import lombok.Getter;

@Getter
public class Method {
    private short accessFlags;
    private String name;
    private String descriptor;

    private int maxStack;
    private int maxLocals;
    private byte[] code;

    public Method(JClass clazz, MethodInfo info) {
        this.accessFlags = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
        CodeAttr codeAttr = info.getCodeAttr();
        if (codeAttr != null) {
            this.maxStack = codeAttr.getMaxStack();
            this.maxLocals = codeAttr.getMaxLocals();
            this.code = codeAttr.getCode();
        }
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

    public boolean isSynchronized() {
        return (accessFlags & AccessFlag.ACC_SYNCHRONIZED) != 0;
    }

    public boolean isBridge() {
        return (accessFlags & AccessFlag.ACC_BRIDGE) != 0;
    }

    public boolean isVarargs() {
        return (accessFlags & AccessFlag.ACC_VARARGS) != 0;
    }

    public boolean isNative() {
        return (accessFlags & AccessFlag.ACC_NATIVE) != 0;
    }

    public boolean isAbstract() {
        return (accessFlags & AccessFlag.ACC_ABSTRACT) != 0;
    }

    public boolean isStrict() {
        return (accessFlags & AccessFlag.ACC_STRICT) != 0;
    }
}
