package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public class StringInfo extends AbstractConstantInfo {
    private short stringIndex;

    public StringInfo(int tag, short stringIndex) {
        super(tag);
        this.stringIndex = stringIndex;
    }
}
