package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public class IntegerInfo extends AbstractConstantInfo {
    public final int value;

    public IntegerInfo(int tag, int value) {
        super(tag);
        this.value = value;
    }
}
