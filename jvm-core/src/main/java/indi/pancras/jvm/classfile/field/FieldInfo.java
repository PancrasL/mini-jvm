package indi.pancras.jvm.classfile.field;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.ConstantValueAttr;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import lombok.Getter;

@Getter
public class FieldInfo {
    private final short accessFlags;
    private final short nameIndex;
    private final short descriptorIndex;
    private final BaseAttr[] attributes;

    private final ClassReader reader;
    private final ConstantPool pool;

    public static FieldInfo[] readFields(ClassReader reader, ConstantPool pool) {
        short cnt = reader.readShort();
        FieldInfo[] fields = new FieldInfo[cnt];
        for (int i = 0; i < cnt; i++) {
            fields[i] = new FieldInfo(reader, pool);
        }
        return fields;
    }

    public FieldInfo(ClassReader reader, ConstantPool pool) {
        accessFlags = reader.readShort();
        nameIndex = reader.readShort();
        descriptorIndex = reader.readShort();
        attributes = BaseAttr.readAttributes(reader, pool);

        this.reader = reader;
        this.pool = pool;
    }

    public String getName() {
        return pool.getUtf8(nameIndex);
    }

    public String getDescriptor() {
        return pool.getUtf8(descriptorIndex);
    }

    public int getConstValueIndex() {
        for (BaseAttr attribute : attributes) {
            if (attribute instanceof ConstantValueAttr) {
                ConstantValueAttr attr = (ConstantValueAttr) attribute;
                return attr.getConstantValueIndex();
            }
        }
        return 0;
    }
}
