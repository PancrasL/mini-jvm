package indi.pancras.jvm.classfile;

import indi.pancras.jvm.classfile.attribute.BaseAttr;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClassFile {
    public static final int MAGIC_NUM = 0xCAFEBABE;
    public static final String MAIN_NAME = "main";
    public static final String MAIN_DESCRIPTOR = "([Ljava/lang/String;)V";

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

    public MethodInfo getMainMethod() {
        for (MethodInfo method : methods) {
            if (method.getName().equals(MAIN_NAME) &&
                    method.getDescriptor().equals(MAIN_DESCRIPTOR)) {
                return method;
            }
        }
        return null;
    }
}
