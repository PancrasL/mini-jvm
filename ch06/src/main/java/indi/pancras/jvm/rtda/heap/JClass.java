package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.base.AccessFlag;
import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.base.Slot;
import lombok.Getter;
import lombok.Setter;

@Getter
public class JClass {
    /**
     * 通过ClassFile获取
     */
    private final ClassFile classFile;
    private final int accessFlags;
    private final String className;
    private final String superClassName;
    private final List<String> interfaceNames;
    private final List<Field> fields;
    private final List<Method> methods;
    private final RuntimeConstantPool constantPool;

    /**
     * 需要外部注入
     */
    @Setter
    private JClassLoader classLoader;
    @Setter
    private JClass superClass;
    @Setter
    private List<JClass> interfaces;
    @Setter
    private int instanceSlotCount;
    @Setter
    private int staticSlotCount;
    @Setter
    private List<Slot> staticFields;

    public JClass(ClassFile classFile) {
        this.classFile = classFile;
        this.accessFlags = classFile.getAccessFlag();
        this.className = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();

        // 属性信息
        fields = new ArrayList<>();
        FieldInfo[] fieldInfos = classFile.getFields();
        for (FieldInfo info : fieldInfos) {
            fields.add(new Field(this, info));
        }
        // 方法信息
        methods = new ArrayList<>();
        MethodInfo[] methodsInfos = classFile.getMethods();
        for (MethodInfo info : methodsInfos) {
            methods.add(new Method(this, info));
        }
        this.constantPool = new RuntimeConstantPool(this, classFile.getConstantPool());
    }

    public boolean canBeAccessedBy(JClass other) {
        // 类是共有的 or 类在同一个包下
        return isPublic() || (getPackageName().equals(other.getPackageName()));
    }

    public String getPackageName() {
        int i = className.lastIndexOf('/');
        return i == -1 ? "" : className.substring(0, i);
    }

    // 静态属性赋值
    public void setInt(int slotId, int val) {
        staticFields.set(slotId, new Slot(val));
    }

    public int getInt(int slotId) {
        return staticFields.get(slotId).getVal();
    }

    public void setFloat(int slotId, float val) {
        setInt(slotId, Float.floatToIntBits(val));
    }

    public float getFloat(int slotId) {
        int val = staticFields.get(slotId).getVal();
        return Float.intBitsToFloat(val);
    }

    public void setLong(int slotId, long val) {
        int low = (int) (val);
        int high = (int) (val >> 32);
        setInt(slotId, high);
        setInt(slotId + 1, low);
    }

    public long getLong(int slotId) {
        int high = staticFields.get(slotId).getVal();
        int low = staticFields.get(slotId + 1).getVal();
        return (((long) high) << 32) | ((long) low & 0x0ffffffffL);
    }

    public void setDouble(int slotId, double val) {
        setLong(slotId, Double.doubleToLongBits(val));
    }

    public double getDouble(int slotId) {
        long val = getLong(slotId);
        return Double.longBitsToDouble(val);
    }

    public void setRef(int slotId, Reference ref) {
        staticFields.set(slotId, new Slot(ref));
    }

    public Reference getRef(int slotId) {
        return staticFields.get(slotId).getRef();
    }

    // 类属性判断
    public boolean isSubClassOf(JClass other) {
        for (JClass c = this.superClass; c != null; c = c.superClass) {
            if (c == other) {
                return true;
            }
        }
        return false;
    }

    public boolean isPublic() {
        return (accessFlags & AccessFlag.ACC_PUBLIC) != 0;
    }

    public boolean isFinal() {
        return (accessFlags & AccessFlag.ACC_FINAL) != 0;
    }

    public boolean isSuper() {
        return (accessFlags & AccessFlag.ACC_SUPER) != 0;
    }

    public boolean isInterface() {
        return (accessFlags & AccessFlag.ACC_INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (accessFlags & AccessFlag.ACC_ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & AccessFlag.ACC_SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (accessFlags & AccessFlag.ACC_ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (accessFlags & AccessFlag.ACC_ENUM) != 0;
    }

}
