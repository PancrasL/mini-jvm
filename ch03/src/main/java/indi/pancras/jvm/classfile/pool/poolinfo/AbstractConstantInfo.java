package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public abstract class AbstractConstantInfo {
    public final int tag;

    public AbstractConstantInfo(int tag) {
        this.tag = tag;
    }
}
