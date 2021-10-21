package indi.pancras.jvm.classfile.pool.poolinfo;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;

/**
 * @author PancrasL
 */
public class ClassInfo extends BaseConstantInfo {
    private short classNameIndex;

    public ClassInfo(int tag, short classNameIndex) {
        super(tag);
        this.classNameIndex = classNameIndex;
    }
}
