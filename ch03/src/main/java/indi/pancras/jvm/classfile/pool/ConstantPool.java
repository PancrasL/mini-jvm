package indi.pancras.jvm.classfile.pool;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.pool.poolinfo.AbstractConstantInfo;
import indi.pancras.jvm.classfile.pool.poolinfo.ConstantInfoFactory;

/**
 * @author PancrasL
 */
public class ConstantPool {
    private int poolSize;
    private ClassReader reader;
    private AbstractConstantInfo[] abstractConstantInfos;

    public ConstantPool(int poolSize, ClassReader reader) {
        this.poolSize = poolSize;
        this.reader = reader;
        abstractConstantInfos = new AbstractConstantInfo[this.poolSize];
        readPoolInfos();
    }

    /**
     * 常量池索引从1开始
     */
    private void readPoolInfos() {
        for (int i = 1; i < poolSize; i++) {
            byte tag = reader.readByte();
            abstractConstantInfos[i] = ConstantInfoFactory.createConstantInfo(tag, reader);
            if (tag == TagCode.CONSTANT_TAG_DOUBLE || tag == TagCode.CONSTANT_TAG_LONG) {
                i++;
            }
        }
    }

    public String getUtf8(int index) {
        return "";
    }
}
