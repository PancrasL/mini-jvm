package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.AbstractAttribute;
import indi.pancras.jvm.classfile.pool.ConstantPool;

/**
 * @author PancrasL
 */
public class CodeAttr extends AbstractAttribute {
    private int maxStack;
    private int maxLocals;

    private byte[] code;

    private AbstractAttribute[] attributes;

    private ConstantPool pool;
    private ExceptionTableEntry[] exceptionTable;

    public CodeAttr()

    public static CodeAttr readCodeAttr(ClassReader reader) {

    }

    static class ExceptionTableEntry {

        private int startPc;
        private int endPc;
        private int handlerPc;
        private int catchType;

        private ExceptionTableEntry(ClassReader reader) {
            this.startPc = reader.nextU2ToInt();
            this.endPc = reader.nextU2ToInt();
            this.handlerPc = reader.nextU2ToInt();
            this.catchType = reader.nextU2ToInt();
        }

        static ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
            int length = reader.nextU2ToInt();
            ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[length];

            for (int i = 0; i < length; i++) {
                exceptionTable[i] = new ExceptionTableEntry(reader);
            }
            return exceptionTable;
        }
    }
}
