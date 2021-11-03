package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;


public class LineNumberTableAttr extends BaseAttr {
    private LineNumberTableEntry[] lineNumberTable;

    public LineNumberTableAttr(ClassReader reader) {
        short cnt = reader.readShort();
        lineNumberTable = new LineNumberTableEntry[cnt];
        for (int i = 0; i < cnt; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader);
        }
    }

    private static class LineNumberTableEntry {
        private final short startPc;
        private final short lineNumber;

        public LineNumberTableEntry(ClassReader reader) {
            startPc = reader.readShort();
            lineNumber = reader.readShort();
        }
    }
}
