package indi.pancras.jvm.rtda.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.MethodRefInfo;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.utils.LookupUtil;
import lombok.Getter;

public class MethodRef extends BaseSymbolRef {
    @Getter
    private final String methodName;
    @Getter
    private final String descriptor;
    private Method method;

    public MethodRef(RuntimeConstantPool pool, MethodRefInfo info) {
        super(pool, pool.getClassName(info.getClassIndex()));
        String[] nameAndType = pool.getNameAndType(info.getNameAndTypeIndex());
        methodName = nameAndType[0];
        descriptor = nameAndType[1];
    }

    public Method resolvedMethod() {
        if (method == null) {
            method = resolveMethodRef();
        }
        return method;
    }

    // 在类d中调用c.method
    // 需要保证d有权限访问c.method
    private Method resolveMethodRef() {
        JClass d = pool.getClazz();
        JClass c = getTargetClazz();

        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        Method m = lookupMethod(c, this.methodName, this.descriptor);
        if (m == null) {
            throw new NoSuchMethodError();
        }
        if (!m.canBeAccessedBy(d)) {
            throw new IllegalAccessError();
        }
        return m;
    }

    /**
     * @return method or null
     */
    private Method lookupMethod(JClass c, String methodName, String descriptor) {
        return LookupUtil.lookupMethodInClass(c, methodName, descriptor, true);
    }
}
