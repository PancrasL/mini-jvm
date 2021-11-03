package indi.pancras.jvm.rtda.base;

public class AccessFlag {
    /**
     * class field method
     */
    public static int ACC_PUBLIC = 0x0001;
    /**
     * field method
     */
    public static int ACC_PRIVATE = 0x0002;
    /**
     * field method
     */
    public static int ACC_PROTECTED = 0x0004;
    /**
     * field method
     */
    public static int ACC_STATIC = 0x0008;
    /**
     * class field method
     */
    public static int ACC_FINAL = 0x0010;
    /**
     * class
     */
    public static int ACC_SUPER = 0x0020;
    /**
     * method
     */
    public static int ACC_SYNCHRONIZED = 0x0020;
    /**
     * field
     */
    public static int ACC_VOLATILE = 0x0040;
    /**
     * method
     */
    public static int ACC_BRIDGE = 0x0040;
    /**
     * field
     */
    public static int ACC_TRANSIENT = 0x0080;
    /**
     * method
     */
    public static int ACC_VARARGS = 0x0080;
    /**
     * method
     */
    public static int ACC_NATIVE = 0x0100;
    /**
     * class
     */
    public static int ACC_INTERFACE = 0x0200;
    /**
     * class       method
     */
    public static int ACC_ABSTRACT = 0x0400;
    /**
     * method
     * <p>
     * strictfp，方法使用 FP-strict 浮点格式
     * </p>
     */
    public static int ACC_STRICT = 0x0800;
    /**
     * class field method
     * <p>
     * 标识并非 Java 源码生成的代码。
     * </p>
     */
    public static int ACC_SYNTHETIC = 0x1000;
    /**
     * class
     */
    public static int ACC_ANNOTATION = 0x2000;
    /**
     * class field
     */
    public static int ACC_ENUM = 0x4000;
}
