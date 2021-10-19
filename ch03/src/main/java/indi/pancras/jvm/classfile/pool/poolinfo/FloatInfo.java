package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.AbstractConstantInfo;

/**
 * @author PancrasL
 */
public class FloatInfo extends AbstractConstantInfo {
    private float value;

    public FloatInfo(int tag, float value) {
        super(tag);
        this.value = value;
    }
}
