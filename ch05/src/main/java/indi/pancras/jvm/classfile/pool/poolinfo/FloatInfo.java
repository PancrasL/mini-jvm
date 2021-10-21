package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;

/**
 * @author PancrasL
 */
public class FloatInfo extends BaseConstantInfo {
    private float value;

    public FloatInfo(int tag, float value) {
        super(tag);
        this.value = value;
    }
}
