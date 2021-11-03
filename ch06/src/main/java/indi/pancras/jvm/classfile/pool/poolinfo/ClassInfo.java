package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import lombok.Getter;

@Getter
public class ClassInfo extends BaseConstantInfo {
    private final short classNameIndex;

    public ClassInfo(int tag, short classNameIndex) {
        super(tag);
        this.classNameIndex = classNameIndex;
    }
}
