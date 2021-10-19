package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.AbstractAttribute;

/**
 * @author PancrasL
 */
public class ConstantValueAttr extends AbstractAttribute {
    private short constantValueIndex;

    public ConstantValueAttr(ClassReader reader) {
        this.constantValueIndex = reader.readShort();
    }
}
