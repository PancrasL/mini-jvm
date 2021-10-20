package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;

/**
 * @author PancrasL
 */
public class UnparsedAttr extends BaseAttr {
    private String name;
    private int length;
    private byte[] data;

    public UnparsedAttr(String name, int length, ClassReader reader) {
        this.name = name;
        this.length = length;
        data = reader.readBytes(length);
    }
}
