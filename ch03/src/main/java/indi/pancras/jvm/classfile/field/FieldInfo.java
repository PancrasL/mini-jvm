package indi.pancras.jvm.classfile.field;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.AbstractAttribute;
import indi.pancras.jvm.classfile.pool.ConstantPool;

/**
 * @author PancrasL
 */
public class FieldInfo {
    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    private AbstractAttribute[] attributes;

    private ClassReader reader;
    private ConstantPool pool;

    public FieldInfo(ClassReader reader, ConstantPool pool) {
        accessFlags = reader.readShort();
        nameIndex = reader.readShort();
        descriptorIndex = reader.readShort();
        attributes = AbstractAttribute.readAttributes(reader, pool);

        this.reader = reader;
        this.pool = pool;
    }

    public static FieldInfo[] readFields(ClassReader reader, ConstantPool pool) {
        short cnt = reader.readShort();
        FieldInfo[] fields = new FieldInfo[cnt];
        for (int i = 0; i < cnt; i++) {
            fields[i] = new FieldInfo(reader, pool);
        }
        return fields;
    }
}
