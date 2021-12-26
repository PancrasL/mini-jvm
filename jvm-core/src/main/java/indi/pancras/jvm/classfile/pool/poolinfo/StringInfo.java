package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class StringInfo extends BaseConstantInfo {
    private final short stringIndex;

    public StringInfo(int tag, short stringIndex) {
        super(tag);
        this.stringIndex = stringIndex;
    }
}
