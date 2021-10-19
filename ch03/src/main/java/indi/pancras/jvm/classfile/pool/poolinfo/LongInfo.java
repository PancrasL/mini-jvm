package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.AbstractConstantInfo;

/**
 * @author PancrasL
 */
public class LongInfo extends AbstractConstantInfo {
    private long value;

    public LongInfo(int tag, long value) {
        super(tag);
        this.value = value;
    }
}
