package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;


public class UnparsedAttr extends BaseAttr {
    private final String name;
    private final int length;
    private final byte[] data;

    public UnparsedAttr(String name, int length, ClassReader reader) {
        this.name = name;
        this.length = length;
        data = reader.readBytes(length);
    }
}
