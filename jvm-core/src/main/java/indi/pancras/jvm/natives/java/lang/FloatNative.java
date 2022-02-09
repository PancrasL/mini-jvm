package indi.pancras.jvm.natives.java.lang;

import indi.pancras.jvm.natives.NativeMethod;
import indi.pancras.jvm.natives.NativeRegistry;
import indi.pancras.jvm.rtda.stack.Frame;

public class FloatNative {
    private static final String jlFloat = "java/lang/Float";

    public static void init() {
        NativeRegistry.INSTANCE.register(jlFloat,
                "floatToRawIntBits",
                "(F)I", new FloatToRawIntBits());
    }

    private static class FloatToRawIntBits implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            float value = frame.getLocalVars().getFloat(0);
            int bits = Float.floatToIntBits(value);
            frame.getOperandStack().pushInt(bits);
        }
    }
}
