package indi.pancras.jvm.natives.java.lang;

import indi.pancras.jvm.natives.NativeMethod;
import indi.pancras.jvm.natives.NativeRegistry;
import indi.pancras.jvm.rtda.JClassLoader;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.heap.StringPool;
import indi.pancras.jvm.rtda.stack.Frame;

public class ClassNative {
    private static final String jlClass = "java/lang/Class";

    public static void init() {
        NativeRegistry.INSTANCE.register(jlClass, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", new GetPrimitiveClass());
        NativeRegistry.INSTANCE.register(jlClass, "getName0", "()Ljava/lang/String;", new GetName0());
        NativeRegistry.INSTANCE.register(jlClass, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", new DesiredAssertionStatus0());
    }

    private static class GetPrimitiveClass implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            // 静态方法，先从局部变量中拿到类名
            JObject jStr = frame.getLocalVars().getRef(0).getTarget();
            byte[] values = jStr.getFieldValue("value", "[C").getTarget().bytes();
            String name = new String(values);

            // 把类对象引用推入操作数栈顶
            JClassLoader loader = frame.getMethod().getClazz().getClassLoader();
            Reference clazzObj = loader.loadClass(name).getClazzObj();
            frame.getOperandStack().pushRef(clazzObj);
        }
    }

    private static class GetName0 implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            JObject thisPtr = frame.getLocalVars().getThis();
            JClass clazz = thisPtr.getExtra();
            String name = clazz.getJavaName();
            Reference nameObj = StringPool.getJString(clazz.getClassLoader(), name);
            frame.getOperandStack().pushRef(nameObj);
        }
    }

    private static class DesiredAssertionStatus0 implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            frame.getOperandStack().pushInt(0);
        }
    }
}