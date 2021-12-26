package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;


public class MethodHandleInfo extends BaseConstantInfo {
    private final byte referenceKind;
    private final short referenceIndex;

    public MethodHandleInfo(int tag, byte referenceKind, short referenceIndex) {
        super(tag);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }
}
