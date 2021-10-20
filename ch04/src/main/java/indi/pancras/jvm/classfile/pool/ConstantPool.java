package indi.pancras.jvm.classfile.pool;

import indi.pancras.jvm.classfile.ClassReader;
import lombok.Getter;

/**
 * @author PancrasL
 */
@Getter
public class ConstantPool {
    private int poolSize;
    private BaseConstantInfo[] constantInfos;

    /**
     * 常量池项个数，因CONSTANT_DOUBLE和CONSTANT_LONG常量占用两个槽，因此poolCount和poolSize不一定相等。
     */
    private int poolCount;

    public ConstantPool(int poolSize, int poolCount, BaseConstantInfo[] constantInfos) {
        this.poolSize = poolSize;
        this.poolCount = poolCount;
        this.constantInfos = constantInfos;
    }

    public static ConstantPool readConstantPool(ClassReader reader) {
        short poolSize = reader.readShort();
        BaseConstantInfo[] infos = new BaseConstantInfo[poolSize];
        int cnt = 0;
        for (int i = 1; i < poolSize; i++) {
            infos[i] = BaseConstantInfo.readConstantInfo(reader);
            int tag = infos[i].tag;
            if (tag == ConstantPoolTag.CONSTANT_TAG_DOUBLE || tag == ConstantPoolTag.CONSTANT_TAG_LONG) {
                i++;
            }
            cnt++;
        }

        return new ConstantPool(poolSize, cnt, infos);
    }

    public String getUTF8(int index) {
        return constantInfos[index].toString();
    }
}
