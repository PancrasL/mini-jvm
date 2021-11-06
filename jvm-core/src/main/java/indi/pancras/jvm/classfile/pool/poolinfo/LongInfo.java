package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import lombok.Getter;

@Getter
public class LongInfo extends BaseConstantInfo {
    private final long value;

    public LongInfo(int tag, long value) {
        super(tag);
        this.value = value;
    }
}
