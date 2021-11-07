package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.AccessFlag;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.utils.SlotsUtil;
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
    private Slot[] staticFields;

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


    public String getPackageName() {
        int i = className.lastIndexOf('/');
        return i == -1 ? "" : className.substring(0, i);
    }

    /**
     * @return mainMethod or null
     */
    public Method getMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    /**
     * @return static method or null
     */
    private Method getStaticMethod(String methodName, String descriptor) {
        for (Method method : methods) {
            if (method.isStatic()) {
                if (method.getMethodName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public boolean canBeAccessedBy(JClass other) {
        // 类是共有的 or 类在同一个包下
        return isPublic() || (getPackageName().equals(other.getPackageName()));
    }

    /**
     * 判断当前类是否可以转换为另一个类（该类的父类）或另一个接口（该类实现了接口）
     *
     * @param other the other clazz
     * @return true or false
     */
    public boolean isAssignableFrom(JClass other) {
        if (this.equals(other)) {
            return true;
        }
        // 判断该类是否为某个类的子类
        if (!this.isInterface()) {
            return this.isSubClassOf(other);
        }
        // 判断该类是否实现了某个接口
        else {
            return this.isImplements(other);
        }
    }

    /**
     * 判断当前类是否直接或间接继承于other
     *
     * @param other the other class
     * @return true or false
     */
    public boolean isSubClassOf(JClass other) {
        for (JClass c = this.superClass; c != null; c = c.superClass) {
            if (c.equals(other)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前类是否实现了接口otheriFace
     *
     * @param otheriFace the other interface
     * @return true or false
     */
    public boolean isImplements(JClass otheriFace) {
        // 从当前类的接口中、父类的接口中以及当前类的接口的父类接口中查找
        for (JClass c = this; c != null; c = c.superClass) {
            for (JClass iface : c.getInterfaces()) {
                if (iface.equals(otheriFace) || iface.isSubInterfaceOf(otheriFace)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前接口是否直接或间接继承自otheriFace接口
     *
     * @param otheriFace the other interface
     * @return true or false
     */
    public boolean isSubInterfaceOf(JClass otheriFace) {
        for (JClass iface : interfaces) {
            return iface.equals(otheriFace) || iface.isSubInterfaceOf(otheriFace);
        }
        return false;
    }

    // 静态属性赋值
    public void setInt(int slotId, int val) {
        SlotsUtil.setInt(staticFields, slotId, val);
    }

    public int getInt(int slotId) {
        return SlotsUtil.getInt(staticFields, slotId);
    }

    public void setFloat(int slotId, float val) {
        SlotsUtil.setFloat(staticFields, slotId, val);
    }

    public float getFloat(int slotId) {
        return SlotsUtil.getFloat(staticFields, slotId);
    }

    public void setLong(int slotId, long val) {
        SlotsUtil.setLong(staticFields, slotId, val);
    }

    public long getLong(int slotId) {
        return SlotsUtil.getLong(staticFields, slotId);
    }

    public void setDouble(int slotId, double val) {
        SlotsUtil.setDouble(staticFields, slotId, val);
    }

    public double getDouble(int slotId) {
        return SlotsUtil.getDouble(staticFields, slotId);
    }

    public void setRef(int slotId, Reference ref) {
        SlotsUtil.setRef(staticFields, slotId, ref);
    }

    public Reference getRef(int slotId) {
        return SlotsUtil.getRef(staticFields, slotId);
    }

    //类属性判断
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
