package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public class MethodHandleInfo extends AbstractConstantInfo {
    private byte referenceKind;
    private short referenceIndex;

    public MethodHandleInfo(int tag, byte referenceKind, short referenceIndex) {
        super(tag);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }
}
