package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class DoubleInfo extends BaseConstantInfo {
    private double value;

    public DoubleInfo(int tag, double value) {
        super(tag);
        this.value = value;
    }
}
