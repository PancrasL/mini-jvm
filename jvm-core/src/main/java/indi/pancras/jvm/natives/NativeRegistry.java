package indi.pancras.jvm.natives;

import java.util.HashMap;

import indi.pancras.jvm.natives.java.lang.ClassNative;
import indi.pancras.jvm.natives.java.lang.FloatNative;
import indi.pancras.jvm.natives.java.lang.ObjectNative;
import indi.pancras.jvm.natives.java.lang.SystemNative;
import indi.pancras.jvm.rtda.stack.Frame;

public enum NativeRegistry {
    INSTANCE;

    public static void init() {
        ObjectNative.init();
        ClassNative.init();
        SystemNative.init();
        FloatNative.init();
    }

    private final HashMap<String, NativeMethod> map = new HashMap<>();

    public void register(String className, String methodName, String methodDescriptor, NativeMethod method) {
        String key = className + "@" + methodName + "@" + methodDescriptor;
        map.put(key, method);
    }

    public NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "@" + methodName + "@" + methodDescriptor;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (methodDescriptor.equals("()V") && methodName.equals("registerNatives")) {
            return new EmptyNativeMethod();
        }
        return null;
    }

    static class EmptyNativeMethod implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            // do nothing
        }
    }
}