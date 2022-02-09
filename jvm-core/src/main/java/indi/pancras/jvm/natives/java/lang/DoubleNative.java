package indi.pancras.jvm.natives.java.lang;

import indi.pancras.jvm.natives.NativeMethod;
import indi.pancras.jvm.natives.NativeRegistry;
import indi.pancras.jvm.rtda.stack.Frame;

public class DoubleNative {
    private static final String jlDouble = "java/lang/Double";

    public static void init() {
        NativeRegistry.INSTANCE.register(jlDouble,
                "doubleToRawLongBits",
                "(D)J", new DoubleToRawIntBits());
    }

    private static class DoubleToRawIntBits implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            double value = frame.getLocalVars().getDouble(0);
            long bits = Double.doubleToLongBits(value);
            frame.getOperandStack().pushLong(bits);
        }
    }
}
