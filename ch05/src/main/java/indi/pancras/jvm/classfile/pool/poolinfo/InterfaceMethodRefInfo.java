package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class InterfaceMethodRefInfo extends BaseConstantInfo {
    private short classIndex;
    private short nameAndTypeIndex;

    public InterfaceMethodRefInfo(int tag, short classIndex, short nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
