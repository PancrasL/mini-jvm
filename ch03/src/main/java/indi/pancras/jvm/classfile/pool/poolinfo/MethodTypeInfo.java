package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.AbstractConstantInfo;

/**
 * @author PancrasL
 */
public class MethodTypeInfo extends AbstractConstantInfo {
    private short descriptorIndex;

    public MethodTypeInfo(int tag, short descriptorIndex) {
        super(tag);
        this.descriptorIndex = descriptorIndex;
    }
}
