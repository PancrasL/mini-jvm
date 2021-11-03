package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import lombok.Getter;

@Getter
public class FloatInfo extends BaseConstantInfo {
    private final float value;

    public FloatInfo(int tag, float value) {
        super(tag);
        this.value = value;
    }
}
