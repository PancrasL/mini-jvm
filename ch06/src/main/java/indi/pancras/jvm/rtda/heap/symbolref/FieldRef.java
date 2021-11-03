package indi.pancras.jvm.rtda.heap.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.FieldRefInfo;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;

public class FieldRef extends SymbolRef {
    private String fieldName;
    private String descriptor;
    private Field field;

    public FieldRef(RuntimeConstantPool pool, FieldRefInfo info) {
        super(pool, pool.getClassName(info.getClassIndex()));
        String[] ss = pool.getNameAndType(info.getNameAndTypeIndex());
        fieldName = ss[0];
        descriptor = ss[1];
    }
}
