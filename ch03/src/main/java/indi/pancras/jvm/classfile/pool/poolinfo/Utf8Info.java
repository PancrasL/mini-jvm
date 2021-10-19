package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.AbstractConstantInfo;

/**
 * @author PancrasL
 */
public class Utf8Info extends AbstractConstantInfo {
    private String value;

    public Utf8Info(int tag, byte[] bytes) {
        super(tag);
        value = new String(bytes);
    }

    @Override
    public String toString() {
        return value;
    }
}
