package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;

/**
 * @author PancrasL
 */
public class MethodHandleInfo extends BaseConstantInfo {
    private byte referenceKind;
    private short referenceIndex;

    public MethodHandleInfo(int tag, byte referenceKind, short referenceIndex) {
        super(tag);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }
}
