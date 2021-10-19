package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public class FloatInfo extends AbstractConstantInfo {
    private float value;

    public FloatInfo(int tag, float value) {
        super(tag);
        this.value = value;
    }
}
