package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.AbstractConstantInfo;

/**
 * @author PancrasL
 */
public class DoubleInfo extends AbstractConstantInfo {
    private double value;

    public DoubleInfo(int tag, double value) {
        super(tag);
        this.value = value;
    }
}
