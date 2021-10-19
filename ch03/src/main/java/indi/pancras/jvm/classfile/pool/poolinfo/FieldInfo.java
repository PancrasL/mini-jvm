package indi.pancras.jvm.classfile.pool.poolinfo;

/**
 * @author PancrasL
 */
public class FieldInfo extends AbstractConstantInfo {
    private short classIndex;
    private short nameAndTypeIndex;

    public FieldInfo(int tag, short classIndex, short nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
