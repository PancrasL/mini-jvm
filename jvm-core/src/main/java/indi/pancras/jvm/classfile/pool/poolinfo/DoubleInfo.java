package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import lombok.Getter;

@Getter
public class DoubleInfo extends BaseConstantInfo {
    private final double value;

    public DoubleInfo(int tag, double value) {
        super(tag);
        this.value = value;
    }
}
