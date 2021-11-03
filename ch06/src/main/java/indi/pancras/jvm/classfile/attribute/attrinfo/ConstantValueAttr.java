package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;


public class ConstantValueAttr extends BaseAttr {
    private short constantValueIndex;

    public ConstantValueAttr(ClassReader reader) {
        this.constantValueIndex = reader.readShort();
    }
}
