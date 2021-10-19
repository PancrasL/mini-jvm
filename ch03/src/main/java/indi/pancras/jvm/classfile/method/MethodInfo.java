package indi.pancras.jvm.classfile.method;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.AbstractAttribute;
import indi.pancras.jvm.classfile.pool.ConstantPool;

/**
 * @author PancrasL
 */
public class MethodInfo {
    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    private AbstractAttribute[] attributes;

    private ClassReader reader;
    private ConstantPool pool;

    public MethodInfo(ClassReader reader, ConstantPool pool) {
        accessFlags = reader.readShort();
        nameIndex = reader.readShort();
        descriptorIndex = reader.readShort();
        attributes = AbstractAttribute.readAttributes(reader, pool);

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
}
