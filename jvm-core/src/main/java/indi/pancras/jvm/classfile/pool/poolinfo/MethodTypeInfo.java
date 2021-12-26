package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class MethodTypeInfo extends BaseConstantInfo {
    private final short descriptorIndex;

    public MethodTypeInfo(int tag, short descriptorIndex) {
        super(tag);
        this.descriptorIndex = descriptorIndex;
    }
}
