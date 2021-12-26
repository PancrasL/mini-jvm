package indi.pancras.jvm.rtda.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.ClassInfo;
import indi.pancras.jvm.rtda.RuntimeConstantPool;

public class ClassRef extends BaseSymbolRef {

    public ClassRef(RuntimeConstantPool pool, ClassInfo info) {
        super(pool, pool.getUtf8(info.getClassNameIndex()));
    }
}
