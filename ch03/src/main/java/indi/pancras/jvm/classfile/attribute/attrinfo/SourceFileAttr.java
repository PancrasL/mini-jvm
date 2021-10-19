package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.AbstractAttribute;

/**
 * @author PancrasL
 */
public class SourceFileAttr extends AbstractAttribute {
    private short sourceFileIndex;

    public SourceFileAttr(ClassReader reader) {
        sourceFileIndex = reader.readShort();
    }
}
