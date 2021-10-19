package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.AbstractConstantInfo;

/**
 * @author PancrasL
 */
public class ClassInfo extends AbstractConstantInfo {
    private short classNameIndex;

    public ClassInfo(int tag, short classNameIndex) {
        super(tag);
        this.classNameIndex = classNameIndex;
    }
}
