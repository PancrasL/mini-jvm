package indi.pancras.jvm.classfile.attribute.attrinfo;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.BaseAttr;


public class LocalVariableTableAttr extends BaseAttr {
    private LocalVariableTableEntry[] localVariableTbl;

    public LocalVariableTableAttr(ClassReader reader) {
        short cnt = reader.readShort();
        localVariableTbl = new LocalVariableTableEntry[cnt];
        for (int i = 0; i < cnt; i++) {
            localVariableTbl[i] = new LocalVariableTableEntry(reader);
        }
    }

    private static class LocalVariableTableEntry {
        private final short startPc;
        private final short length;
        private final short nameIndex;
        private final short descriptorIndex;
        private final short index;

        public LocalVariableTableEntry(ClassReader reader) {
            startPc = reader.readShort();
            length = reader.readShort();
            nameIndex = reader.readShort();
            descriptorIndex = reader.readShort();
            index = reader.readShort();
        }
    }
}
