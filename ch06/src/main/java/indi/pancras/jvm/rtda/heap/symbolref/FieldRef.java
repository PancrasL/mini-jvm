package indi.pancras.jvm.rtda.heap.symbolref;

import indi.pancras.jvm.classfile.pool.poolinfo.FieldRefInfo;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
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

    public Field getField() {
        if (field == null) {
            field = resolveFieldRef();
        }
        return field;
    }

    private Field resolveFieldRef() {
        // 如果类D想通过字段符号引用访问类C的某个字段，
        // 首先要解析符号引用得到类C，然后根据字段名和描述符查找字段。
        // 如果字段查找失败，则虚拟机抛出NoSuchFieldError异常。
        // 如果查找成功，但D没有足够的权限访问该字段，则虚拟机抛出IllegalAccessError异常。
        JClass d = pool.getJClass();
        JClass c = getResolvedClass();
        field = lookupField(c, fieldName, descriptor);
        if (field == null) {
            throw new NoSuchFieldError(fieldName + descriptor);
        }
        if (!field.canBeAccessedBy(d)) {
            throw new IllegalAccessError(d.toString());
        }
        return field;
    }

    private Field lookupField(JClass clazz, String fieldName, String descriptor) {
        for (Field field : clazz.getFields()) {
            if (field.getName().equals(fieldName) && field.getDescriptor().equals(descriptor)) {
                return field;
            }
        }

        for (JClass iface : clazz.getInterfaces()) {
            Field f = lookupField(iface, fieldName, descriptor);
            if (f != null) {
                return f;
            }
        }

        if (clazz.getSuperClass() != null) {
            return lookupField(clazz.getSuperClass(), fieldName, descriptor);
        }
        return null;
    }
}
