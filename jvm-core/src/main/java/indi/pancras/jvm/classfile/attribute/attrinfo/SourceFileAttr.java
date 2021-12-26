package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;


public class SourceFileAttr extends BaseAttr {
    private final short sourceFileIndex;

    public SourceFileAttr(ClassReader reader) {
        sourceFileIndex = reader.readShort();
    }
}
