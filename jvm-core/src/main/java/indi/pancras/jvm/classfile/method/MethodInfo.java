package indi.pancras.jvm.classfile.method;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.CodeAttr;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import lombok.Getter;


@Getter
public class MethodInfo {
    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    private BaseAttr[] attributes;

    private ClassReader reader;
    private ConstantPool pool;

    public MethodInfo(ClassReader reader, ConstantPool pool) {
        accessFlags = reader.readShort();
        nameIndex = reader.readShort();
        descriptorIndex = reader.readShort();
        attributes = BaseAttr.readAttributes(reader, pool);

        this.reader = reader;
        this.pool = pool;
    }

    public static MethodInfo[] readMethods(ClassReader reader, ConstantPool pool) {
        int cnt = reader.readShort();
        MethodInfo[] methods = new MethodInfo[cnt];
        for (int i = 0; i < cnt; i++) {
            methods[i] = new MethodInfo(reader, pool);
        }
        return methods;
    }

    public String getName() {
        return pool.getUtf8(nameIndex);
    }

    public String getDescriptor() {
        return pool.getUtf8(descriptorIndex);
    }

    public CodeAttr getCodeAttr() {
        for (BaseAttr attribute : attributes) {
            if (attribute instanceof CodeAttr) {
                return (CodeAttr) attribute;
            }
        }
        return null;
    }

}
