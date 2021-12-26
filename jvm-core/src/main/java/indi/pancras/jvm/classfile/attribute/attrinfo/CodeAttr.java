package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import lombok.Getter;

@Getter
public class CodeAttr extends BaseAttr {
    private final short maxStack;
    private final short maxLocals;
    private final byte[] code;
    private final ExceptionTableEntry[] exceptionTable;
    private final BaseAttr[] attributes;

    private final ClassReader reader;
    private final ConstantPool pool;

    public CodeAttr(ClassReader reader, ConstantPool pool) {
        maxStack = reader.readShort();
        maxLocals = reader.readShort();
        int codeLength = reader.readInt();
        code = reader.readBytes(codeLength);
        exceptionTable = ExceptionTableEntry.readExceptionTable(reader);
        attributes = BaseAttr.readAttributes(reader, pool);

        this.reader = reader;
        this.pool = pool;
    }

    private static class ExceptionTableEntry {
        private final int startPc;
        private final int endPc;
        private final int handlerPc;
        private final int catchType;

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
