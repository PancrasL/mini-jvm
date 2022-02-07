package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.classfile.attribute.attrinfo.CodeAttr;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.rtda.AccessFlag;
import indi.pancras.jvm.rtda.DescriptorFlag;
import lombok.Getter;

@Getter
public class Method {
    // 方法所属于的类
    private final JClass clazz;

    private final short accessFlags;
    private final String methodName;
    private final String descriptor;
    private final int argSlotCount;
    private int maxStack;
    private int maxLocals;
    private byte[] code;

    public Method(JClass clazz, MethodInfo info) {
        this.clazz = clazz;
        this.accessFlags = info.getAccessFlags();
        this.methodName = info.getName();
        this.descriptor = info.getDescriptor();
        CodeAttr codeAttr = info.getCodeAttr();
        if (codeAttr != null) {
            this.maxStack = codeAttr.getMaxStack();
            this.maxLocals = codeAttr.getMaxLocals();
            this.code = codeAttr.getCode();
        }
        // 计算参数槽数量
        argSlotCount = calArgSlotCount();
    }

    /**
     * 计算方法的参数在局部变量表中占用多少位置，特别地，需要为非静态方法预留一个this参数位置
     * @return
     */
    private int calArgSlotCount() {
        int slotCount = 0;
        MethodDescriptor parsedDescriptor = new MethodDescriptor(descriptor);
        for (String paramType : parsedDescriptor.getParamTypes()) {
            slotCount++;
            if (paramType.charAt(0) == DescriptorFlag.LONG_FLAG || paramType.charAt(0) == DescriptorFlag.DOUBLE_FLAG) {
                slotCount++;
            }
        }
        // 非静态方法，需要多预留一个槽保存this引用
        if (!isStatic()) {
            slotCount++;
        }
        return slotCount;
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

    public boolean isConstructor(){
        return methodName.equals("<init>");
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

    @Override
    public String toString() {
        return "Method{" +
                "clazz=" + clazz +
                ", methodName='" + methodName + '\'' +
                ", descriptor='" + descriptor + '\'' +
                '}';
    }
}
