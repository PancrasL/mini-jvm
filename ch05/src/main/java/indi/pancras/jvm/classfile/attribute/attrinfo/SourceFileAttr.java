package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;

/**
 * @author PancrasL
 */
public class SourceFileAttr extends BaseAttr {
    private short sourceFileIndex;

    public SourceFileAttr(ClassReader reader) {
        sourceFileIndex = reader.readShort();
    }
}
