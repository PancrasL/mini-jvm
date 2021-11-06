package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import lombok.Getter;

@Getter
public class IntegerInfo extends BaseConstantInfo {
    private final int value;

    public IntegerInfo(int tag, int value) {
        super(tag);
        this.value = value;
    }
}
