package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.classfile.pool.BaseConstantInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import indi.pancras.jvm.classfile.pool.poolinfo.*;
import indi.pancras.jvm.rtda.heap.symbolref.ClassRef;
import indi.pancras.jvm.rtda.heap.symbolref.FieldRef;
import indi.pancras.jvm.rtda.heap.symbolref.MethodRef;

/**
 * 运行时常量池
 */
public class RuntimeConstantPool {

    private final JClass clazz;
    private final ConstantPool pool;

    public RuntimeConstantPool(JClass clazz, ConstantPool pool) {
        this.clazz = clazz;
        this.pool = pool;
    }

    /**
     * 获取当前常量池所属的类变量
     *
     * @return clazz
     */
    public JClass getClazz() {
        return clazz;
    }

    public int getInt(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof IntegerInfo) {
            return ((IntegerInfo) info).getValue();
        }
        throw new RuntimeException("Illegal index, info is not IntegerInfo");
    }

    public float getFloat(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof FloatInfo) {
            return ((FloatInfo) info).getValue();
        }
        throw new RuntimeException("Illegal index, info is not FloatInfo");
    }

    public long getLong(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof LongInfo) {
            return ((LongInfo) info).getValue();
        }
        throw new RuntimeException("Illegal index, info is not LongInfo");
    }

    public double getDouble(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof DoubleInfo) {
            return ((DoubleInfo) info).getValue();
        }
        throw new RuntimeException("Illegal index, info is not DoubleInfo");
    }

    public String getUtf8(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof Utf8Info) {
            return info.toString();
        }
        throw new RuntimeException("Illegal index, info is not Utf8Info");
    }

    public String getClassName(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof ClassInfo) {
            return getUtf8(((ClassInfo) info).getClassNameIndex());
        }
        throw new RuntimeException("Illegal index, info is not ClassInfo");
    }

    public String[] getNameAndType(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof NameAndTypeInfo) {
            NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) info;
            String[] ss = new String[2];
            ss[0] = getUtf8(nameAndTypeInfo.getNameIndex());
            ss[1] = getUtf8(nameAndTypeInfo.getDescriptorIndex());
            return ss;
        }
        throw new RuntimeException("Illegal index, info is not NameAndTypeInfo");
    }

    public ClassRef getClassRef(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof ClassInfo) {
            return new ClassRef(this, (ClassInfo) info);
        }
        throw new RuntimeException("Illegal index, info is not ClassInfo");
    }

    public FieldRef getFieldRef(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof FieldRefInfo) {
            return new FieldRef(this, (FieldRefInfo) info);
        }
        throw new RuntimeException("Illegal index, info is not FieldRefInfo");
    }

    public MethodRef getMethodRef(int index) {
        BaseConstantInfo info = pool.getConstantInfo(index);
        if (info instanceof MethodRefInfo) {
            return new MethodRef(this, (MethodRefInfo) info);
        }
        throw new RuntimeException("Illegal index, info is not MethodRefInfo");
    }

    public String getConstantValueType(int index) {
        BaseConstantInfo constantInfo = pool.getConstantInfo(index);
        if (constantInfo instanceof IntegerInfo) {
            return "int";
        } else if (constantInfo instanceof LongInfo) {
            return "long";
        } else if (constantInfo instanceof FloatInfo) {
            return "float";
        } else if (constantInfo instanceof DoubleInfo) {
            return "double";
        } else if (constantInfo instanceof StringInfo) {
            return "string";
        } else if (constantInfo instanceof ClassInfo) {
            return "ref";
        }
        throw new RuntimeException("Not value type");
    }
}
