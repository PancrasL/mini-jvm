package indi.pancras.jvm.testclass;

import java.io.Closeable;
import java.util.Date;

/**
 * 测试类，共JClassLoaderTest使用
 */
public class StaticField implements Closeable {
    private static final boolean BOOL_VAL = true;
    private static final byte BYTE_VAL = 1;
    private static final char CHAR_VAL = 2;
    private static final short SHORT_VAL = 3;
    private static final int INT_VAL = 4;
    private static final float FLOAT_VAL = 1.1f;
    private static final long LONG_VAL = 100L;
    private static final double DOUBLE_VAL = 1.2d;
    private static final String STRING_VAL = "hello";
    private static final Object OBJECT_VAL = new Date();

    private final int intValue = 100;
    private final double doubleValue = 10.5D;

    public static void main(String[] args) {
        int a = 1;
    }

    public Double test(int a, Double b) {
        return a + b;
    }

    @Override
    public void close() {
        // do nothing
    }
}
