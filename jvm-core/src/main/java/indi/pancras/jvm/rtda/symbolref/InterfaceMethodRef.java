package indi.pancras.jvm.rtda.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.InterfaceMethodRefInfo;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.utils.LookupUtil;
import lombok.Getter;

@Getter
public class InterfaceMethodRef extends BaseSymbolRef {
    private final String methodName;
    private final String descriptor;
    private Method method;

    public InterfaceMethodRef(RuntimeConstantPool pool, InterfaceMethodRefInfo info) {
        super(pool, pool.getClassName(info.getClassIndex()));
        String[] nameAndType = pool.getNameAndType(info.getNameAndTypeIndex());
        methodName = nameAndType[0];
        descriptor = nameAndType[1];
    }

    public Method resolvedMethod() {
        if (method == null) {
            method = resolveInterfaceMethodRef();
        }
        return method;
    }

    private Method resolveInterfaceMethodRef() {
        JClass d = pool.getClazz();
        JClass c = resolvedClass();

        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        Method m = lookupInterfaceMethod(c, this.methodName, this.descriptor);
        if (m == null) {
            throw new NoSuchMethodError();
        }
        if (!m.canBeAccessedBy(d)) {
            throw new IllegalAccessError();
        }
        return m;
    }

    /**
     * 在类c的接口中查找方法名为methodName，方法描述符为descriptor的方法
     *
     * @return method or null
     */
    private Method lookupInterfaceMethod(JClass c, String methodName, String descriptor) {
        return LookupUtil.lookupMethodInInterface(c, methodName, descriptor, true);
    }
}
