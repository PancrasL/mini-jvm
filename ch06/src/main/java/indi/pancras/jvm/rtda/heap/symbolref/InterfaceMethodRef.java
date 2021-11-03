package indi.pancras.jvm.rtda.heap.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.InterfaceMethodRefInfo;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;

public class InterfaceMethodRef extends SymbolRef {
    private String methodName;
    private String descriptor;
    private Method method;

    public InterfaceMethodRef(RuntimeConstantPool pool, InterfaceMethodRefInfo info) {
        super(pool, pool.getClassName(info.getClassIndex()));
        String[] ss = pool.getNameAndType(info.getNameAndTypeIndex());
        methodName = ss[0];
        descriptor = ss[1];
    }
}
