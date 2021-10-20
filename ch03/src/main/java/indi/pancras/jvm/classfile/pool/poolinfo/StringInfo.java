package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;

/**
 * @author PancrasL
 */
public class StringInfo extends BaseConstantInfo {
    private short stringIndex;

    public StringInfo(int tag, short stringIndex) {
        super(tag);
        this.stringIndex = stringIndex;
    }
}
