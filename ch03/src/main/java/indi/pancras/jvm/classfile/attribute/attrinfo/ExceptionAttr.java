package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;

/**
 * @author PancrasL
 */
public class ExceptionAttr extends BaseAttr {
    private short[] exceptionIndexTbl;

    public ExceptionAttr(ClassReader reader) {
        short cnt = reader.readShort();
        exceptionIndexTbl = new short[cnt];
        for (int i = 0; i < cnt; i++) {
            exceptionIndexTbl[i] = reader.readShort();
        }
    }
}
