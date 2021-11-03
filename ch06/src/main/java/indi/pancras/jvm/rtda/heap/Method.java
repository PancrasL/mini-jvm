package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.classfile.attribute.attrinfo.CodeAttr;
import indi.pancras.jvm.classfile.method.MethodInfo;
import lombok.Getter;

@Getter
public class Method {
    private Class clazz;

    private short accessFlags;
    private String name;
    private String descriptor;

    private int maxStack;
    private int maxLocals;
    private byte[] code;

    public Method(Class clazz, MethodInfo info) {
        this.clazz = clazz;
        this.accessFlags = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
        CodeAttr codeAttr = info.getCodeAttr();
        this.maxStack = codeAttr.getMaxStack();
        this.maxLocals = codeAttr.getMaxLocals();
        this.code = codeAttr.getCode();
    }
}
