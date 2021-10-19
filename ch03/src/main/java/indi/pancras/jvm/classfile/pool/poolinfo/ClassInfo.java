package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public class ClassInfo extends AbstractConstantInfo {
    private short classNameIndex;

    public ClassInfo(int tag, short classNameIndex) {
        super(tag);
        this.classNameIndex = classNameIndex;
    }
}
