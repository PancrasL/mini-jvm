package indi.pancras.jvm.classfile.pool;

import indi.pancras.jvm.classfile.ClassReader;

/**
 * @author PancrasL
 */
public class ConstantPool {
    private int poolSize;
    private AbstractConstantInfo[] abstractConstantInfos;

    public ConstantPool(int poolSize, AbstractConstantInfo[] abstractConstantInfos) {
        this.poolSize = poolSize;
        this.abstractConstantInfos = abstractConstantInfos;
    }

    public static ConstantPool readConstantPool(ClassReader reader) {
        short poolSize = reader.readShort();
        AbstractConstantInfo[] infos = new AbstractConstantInfo[poolSize];
        for (int i = 1; i < poolSize; i++) {
            infos[i] = AbstractConstantInfo.readConstantInfo(reader);
            int tag = infos[i].tag;
            if (tag == ConstantPoolTag.CONSTANT_TAG_DOUBLE || tag == ConstantPoolTag.CONSTANT_TAG_LONG) {
                i++;
            }
        }

        return new ConstantPool(poolSize, infos);
    }

    public String getUtf8(int index) {
        return abstractConstantInfos[index].toString();
    }
}
