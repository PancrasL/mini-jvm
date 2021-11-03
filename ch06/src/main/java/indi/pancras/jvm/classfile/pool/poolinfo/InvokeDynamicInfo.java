package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class InvokeDynamicInfo extends BaseConstantInfo {
    private short bootstrapMethodAttrIndex;
    private short nameAndTypeIndex;

    public InvokeDynamicInfo(int tag, short bootstrapMethodAttrIndex, short nameAndTypeIndex) {
        super(tag);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
