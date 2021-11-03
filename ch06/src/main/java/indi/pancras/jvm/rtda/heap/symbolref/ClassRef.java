package indi.pancras.jvm.rtda.heap.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.ClassInfo;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;

public class ClassRef extends SymbolRef {

    public ClassRef(RuntimeConstantPool pool, ClassInfo info) {
        super(pool, pool.getClassName(info.getClassNameIndex()));
    }
}
