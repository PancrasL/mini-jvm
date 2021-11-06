package indi.pancras.jvm.classfile;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classfile.attribute.BaseAttr;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import indi.pancras.jvm.classfile.pool.poolinfo.ClassInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassFile {
    public static final int MAGIC_NUM = 0xCAFEBABE;

    private int magic;
    private short minorVersion;
    private short majorVersion;
    private ConstantPool constantPool;
    private short accessFlag;
    private short thisClassIndex;
    private short superClassIndex;
    private short interfaceCount;
    private int[] interfaceIndexes;
    private int fieldCount;
    private FieldInfo[] fields;
    private int methodCount;
    private MethodInfo[] methods;
    private short attrCount;
    private BaseAttr[] attributes;

    public String getClassName() {
        ClassInfo info = (ClassInfo) constantPool.getConstantInfo(thisClassIndex);
        return constantPool.getUtf8(info.getClassNameIndex());
    }

    public String getSuperClassName() {
        ClassInfo info = (ClassInfo) constantPool.getConstantInfo(superClassIndex);
        return constantPool.getUtf8(info.getClassNameIndex());
    }

    public List<String> getInterfaceNames() {
        ArrayList<String> list = new ArrayList<>();
        for (int i : interfaceIndexes) {
            ClassInfo info = (ClassInfo) constantPool.getConstantInfo(i);
            short nameIndex = info.getClassNameIndex();
            list.add(constantPool.getUtf8(nameIndex));
        }
        return list;
    }
}
