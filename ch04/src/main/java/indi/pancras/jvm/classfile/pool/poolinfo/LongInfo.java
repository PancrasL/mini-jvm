package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;

/**
 * @author PancrasL
 */
public class LongInfo extends BaseConstantInfo {
    private long value;

    public LongInfo(int tag, long value) {
        super(tag);
        this.value = value;
    }
}
