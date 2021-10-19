package indi.pancras.jvm.classfile.pool.poolinfo;

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
