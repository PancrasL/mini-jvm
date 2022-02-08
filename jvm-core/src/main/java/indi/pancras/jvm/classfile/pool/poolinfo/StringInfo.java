package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import lombok.Getter;


public class StringInfo extends BaseConstantInfo {
    @Getter
    private final short stringIndex;

    public StringInfo(int tag, short stringIndex) {
        super(tag);
        this.stringIndex = stringIndex;
    }
}
