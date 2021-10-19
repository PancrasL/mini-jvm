package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.AbstractAttribute;
import indi.pancras.jvm.classfile.pool.ConstantPool;

/**
 * @author PancrasL
 */
public class CodeAttr extends AbstractAttribute {
    private short maxStack;
    private short maxLocals;
    private byte[] code;
    private ExceptionTableEntry[] exceptionTable;
    private AbstractAttribute[] attributes;

    private ClassReader reader;
    private ConstantPool pool;

    public CodeAttr(ClassReader reader, ConstantPool pool) {
        maxStack = reader.readShort();
        maxLocals = reader.readShort();
        int codeLength = reader.readInt();
        code = reader.readBytes(codeLength);
        exceptionTable = ExceptionTableEntry.readExceptionTable(reader);
        attributes = AbstractAttribute.readAttributes(reader, pool);

        this.reader = reader;
        this.pool = pool;
    }

    private static class ExceptionTableEntry {
        private int startPc;
        private int endPc;
        private int handlerPc;
        private int catchType;

        private ExceptionTableEntry(ClassReader reader) {
            this.startPc = reader.readShort();
            this.endPc = reader.readShort();
            this.handlerPc = reader.readShort();
            this.catchType = reader.readShort();
        }

        static ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
            int length = reader.readShort();
            ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[length];

            for (int i = 0; i < length; i++) {
                exceptionTable[i] = new ExceptionTableEntry(reader);
            }
            return exceptionTable;
        }
    }
}
