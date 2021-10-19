package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.AbstractConstantInfo;

/**
 * @author PancrasL
 */
public class FieldRefInfo extends AbstractConstantInfo {
    private short classIndex;
    private short nameAndTypeIndex;

    public FieldRefInfo(int tag, short classIndex, short nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
