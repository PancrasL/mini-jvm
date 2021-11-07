package indi.pancras.jvm.rtda.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.MethodRefInfo;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import lombok.Getter;

@Getter
public class MethodRef extends SymbolRef {
    private final String methodName;
    private final String descriptor;
    private Method method;

    public MethodRef(RuntimeConstantPool pool, MethodRefInfo info) {
        super(pool, pool.getClassName(info.getClassIndex()));
        String[] ss = pool.getNameAndType(info.getNameAndTypeIndex());
        methodName = ss[0];
        descriptor = ss[1];
    }
}
