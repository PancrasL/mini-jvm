package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.classfile.ClassFile;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.rtda.AccessFlag;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.utils.LookupUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JClass {
    /**
     * 通过ClassFile获取
     */
    private ClassFile classFile;
    private int accessFlags;
    private String className;
    private String superClassName;
    private List<String> interfaceNames;
    private List<Field> fields;
    private List<Method> methods;
    private RuntimeConstantPool constantPool;

    /**
     * 需要外部注入
     */
    private JClassLoader classLoader;
    private JClass superClass;
    private List<JClass> interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Slot[] staticFields;
    // 标识类是否被初始化
    private boolean initStarted;

    /**
     * class类所对应的对象
     */
    private Reference clazzObj;

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

    public JClass() {
        methods = new ArrayList<>(0);
        fields = new ArrayList<>(0);
        interfaceNames = new ArrayList<>(0);
    }

    // 创建一个当前类的空实例
    public JObject newInstance() {
        return new JObject(this);
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
        return LookupUtil.lookupStaticMethodInClass(this, methodName, descriptor);
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
        // 是同一个类对象
        if (this == other) {
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
            // 是同一个类对象
            if (c == other) {
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
                if (iface == otheriFace || iface.isSubInterfaceOf(otheriFace)) {
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
            return iface == otheriFace || iface.isSubInterfaceOf(otheriFace);
        }
        return false;
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

    public boolean isSuperClassOf(JClass other) {
        return other.isSubClassOf(this);
    }

    @Override
    public String toString() {
        return "JClass{" +
                "className='" + className + '\'' +
                '}';
    }

    public Method getClinitMethod() {
        return getStaticMethod("<clinit>", "()V");
    }

    public JObject newArray(int count) {
        if (!this.isArray()) {
            throw new RuntimeException("Not array class: " + className);
        }
        switch (className) {
            case "[Z":
            case "[B":
                return new JObject(this, count, ArrayType.BYTE, new byte[count]);
            case "[C":
                return new JObject(this, count, ArrayType.CHAR, new char[count]);
            case "[S":
                return new JObject(this, count, ArrayType.SHORT, new short[count]);
            case "[I":
                return new JObject(this, count, ArrayType.INT, new int[count]);
            case "[J":
                return new JObject(this, count, ArrayType.LONG, new long[count]);
            case "[F":
                return new JObject(this, count, ArrayType.FLOAT, new float[count]);
            case "[D":
                return new JObject(this, count, ArrayType.DOUBLE, new double[count]);
            default:
                return new JObject(this, count, ArrayType.REFERENCE, new Reference[count]);
        }
    }

    /**
     * 返回与类对应的数组类
     *
     * @return clazz[]，clazz为当前类
     */
    public JClass arrayClass() {
        String arrayClassName = JClassNameHelper.getArrayClassName(className);
        return classLoader.loadClass(arrayClassName);
    }

    /**
     * 根据数组类型名推测出数组元素类名，然后用类加载器加载元素类
     *
     * @return 数组元素的类
     */
    public JClass componentClass() {
        String componentClassName = JClassNameHelper.getComponentClassName(this.className);
        return classLoader.loadClass(componentClassName);
    }

    private boolean isArray() {
        return className.charAt(0) == '[';
    }

    public Field getField(String name, String descriptor, boolean isStatic) {
        for (JClass clazz = this; clazz != null; clazz = clazz.superClass) {
            for (Field field : clazz.fields) {
                if (field.isStatic() == isStatic
                        && field.getFieldName().equals(name)
                        && field.getDescriptor().equals(descriptor)) {
                    return field;
                }
            }
        }
        return null;
    }

    public String getJavaName() {
        return className.replace('/', '.');
    }
}
