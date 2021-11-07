package indi.pancras.jvm.rtda;

public interface AccessFlag {
    /**
     * class field method
     */
    int ACC_PUBLIC = 0x0001;
    /**
     * field method
     */
    int ACC_PRIVATE = 0x0002;
    /**
     * field method
     */
    int ACC_PROTECTED = 0x0004;
    /**
     * field method
     */
    int ACC_STATIC = 0x0008;
    /**
     * class field method
     */
    int ACC_FINAL = 0x0010;
    /**
     * class
     */
    int ACC_SUPER = 0x0020;
    /**
     * method
     */
    int ACC_SYNCHRONIZED = 0x0020;
    /**
     * field
     */
    int ACC_VOLATILE = 0x0040;
    /**
     * method
     */
    int ACC_BRIDGE = 0x0040;
    /**
     * field
     */
    int ACC_TRANSIENT = 0x0080;
    /**
     * method
     */
    int ACC_VARARGS = 0x0080;
    /**
     * method
     */
    int ACC_NATIVE = 0x0100;
    /**
     * class
     */
    int ACC_INTERFACE = 0x0200;
    /**
     * class       method
     */
    int ACC_ABSTRACT = 0x0400;
    /**
     * method
     * <p>
     * strictfp，方法使用 FP-strict 浮点格式
     * </p>
     */
    int ACC_STRICT = 0x0800;
    /**
     * class field method
     * <p>
     * 标识并非 Java 源码生成的代码。
     * </p>
     */
    int ACC_SYNTHETIC = 0x1000;
    /**
     * class
     */
    int ACC_ANNOTATION = 0x2000;
    /**
     * class field
     */
    int ACC_ENUM = 0x4000;
}
