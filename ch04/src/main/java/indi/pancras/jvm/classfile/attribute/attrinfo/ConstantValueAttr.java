package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;

/**
 * @author PancrasL
 */
public class ConstantValueAttr extends BaseAttr {
    private short constantValueIndex;

    public ConstantValueAttr(ClassReader reader) {
        this.constantValueIndex = reader.readShort();
    }
}
