package indi.pancras.jvm.natives.java.lang;

import indi.pancras.jvm.natives.NativeMethod;
import indi.pancras.jvm.natives.NativeRegistry;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.stack.Frame;

public class ObjectNative {
    private static final String jlObject = "java/lang/Object";

    public static void init() {
        NativeRegistry.INSTANCE.register(jlObject, "getClass", "()Ljava/lang/Class;", new GetClass());
        NativeRegistry.INSTANCE.register(jlObject, "hashCode", "()I", new HashCode());
    }

    /**
     * 返回对象的类对象引用
     */
    private static class GetClass implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            JObject thisPtr = frame.getLocalVars().getThis();
            Reference clazzObj = thisPtr.getClazz().getClazzObj();
            frame.getOperandStack().pushRef(clazzObj);
        }
    }

    /**
     * 返回对象的哈希码
     */
    private static class HashCode implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            JObject thisPtr = frame.getLocalVars().getThis();
            frame.getOperandStack().pushInt(thisPtr.hashCode());
        }
    }
}


