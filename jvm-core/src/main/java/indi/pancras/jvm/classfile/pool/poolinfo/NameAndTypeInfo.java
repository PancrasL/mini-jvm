package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import lombok.Getter;

@Getter
public class NameAndTypeInfo extends BaseConstantInfo {
    private final short nameIndex;
    private final short descriptorIndex;

    public NameAndTypeInfo(int tag, short nameIndex, short descriptorIndex) {
        super(tag);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }
}
