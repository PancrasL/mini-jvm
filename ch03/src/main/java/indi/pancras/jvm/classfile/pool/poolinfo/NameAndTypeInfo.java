package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class NameAndTypeInfo extends BaseConstantInfo {
    private short nameIndex;
    private short descriptorIndex;

    public NameAndTypeInfo(int tag, short nameIndex, short descriptorIndex) {
        super(tag);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
