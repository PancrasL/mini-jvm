package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public class DoubleInfo extends AbstractConstantInfo {
    private double value;

    public DoubleInfo(int tag, double value) {
        super(tag);
        this.value = value;
    }
}
