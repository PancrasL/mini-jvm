package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class MethodRefInfo extends BaseConstantInfo {
    private short classIndex;
    private short nameAndTypeIndex;

    public MethodRefInfo(int tag, short classIndex, short nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
