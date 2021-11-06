package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;
import lombok.Getter;

@Getter
public class ConstantValueAttr extends BaseAttr {
    private final short constantValueIndex;

    public ConstantValueAttr(ClassReader reader) {
        this.constantValueIndex = reader.readShort();
    }
}
