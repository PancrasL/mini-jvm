package indi.pancras.jvm.classfile.field;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.AbstractAttribute;

/**
 * @author PancrasL
 */
public class FieldInfo {
    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;

    private short attributesCount;
    private AbstractAttribute[] attributes;

    public FieldInfo(short accessFlags, short nameIndex, short descriptorIndex, short attributesCount, AbstractAttribute[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    public static FieldInfo readField(ClassReader reader) {
        short accessFlags = reader.readShort();
        short nameIndex = reader.readShort();
        short descriptorIndex = reader.readShort();
        short attributeCount = reader.readShort();
        AbstractAttribute[] attributes = new AbstractAttribute[attributeCount];
        for (int i = 0; i < attributeCount; i++) {
            attributes[i] = AbstractAttribute.readAttribute(reader);
        }

        return new FieldInfo(accessFlags, nameIndex, descriptorIndex, attributeCount, attributes);
    }
}
