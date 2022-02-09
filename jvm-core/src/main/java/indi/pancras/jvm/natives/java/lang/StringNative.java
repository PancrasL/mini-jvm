package indi.pancras.jvm.natives.java.lang;

import indi.pancras.jvm.natives.NativeMethod;
import indi.pancras.jvm.natives.NativeRegistry;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.heap.StringPool;
import indi.pancras.jvm.rtda.stack.Frame;

public class StringNative {
    private static final String jlString = "java/lang/String";

    public static void init() {
        NativeRegistry.INSTANCE.register(jlString, "intern", "()Ljava/lang/String;", new Intern());
    }

    private static class Intern implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            JObject thisPtr = frame.getLocalVars().getThis();
            Reference interned = StringPool.internString(thisPtr);
            frame.getOperandStack().pushRef(interned);
        }
    }
}
