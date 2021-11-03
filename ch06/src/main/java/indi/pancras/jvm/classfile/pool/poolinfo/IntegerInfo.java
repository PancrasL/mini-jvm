package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class IntegerInfo extends BaseConstantInfo {
    public final int value;

    public IntegerInfo(int tag, int value) {
        super(tag);
        this.value = value;
    }
}
