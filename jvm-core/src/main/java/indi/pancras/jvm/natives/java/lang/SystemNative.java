package indi.pancras.jvm.natives.java.lang;

import indi.pancras.jvm.natives.NativeMethod;
import indi.pancras.jvm.natives.NativeRegistry;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.LocalVars;

public class SystemNative {
    private static final String jlSystem = "java/lang/System";

    public static void init() {
        NativeRegistry.INSTANCE.register(jlSystem, "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", new ArrayCopy());
    }

    private static class ArrayCopy implements NativeMethod {
        @Override
        public void invokeMethod(Frame frame) {
            LocalVars localVars = frame.getLocalVars();
            Reference src = localVars.getRef(0);
            int srcPos = localVars.getInt(1);
            Reference dest = localVars.getRef(2);
            int destPos = localVars.getInt(3);
            int length = localVars.getInt(4);
            if (src.targetIsNull() || dest.targetIsNull()) {
                throw new NullPointerException();
            }
            arrayCopy(src, dest, srcPos, destPos, length);
        }

        private void arrayCopy(Reference src, Reference dest, int srcPos, int destPos, int length) {
            JObject srcArr = src.getTarget();
            JObject destArr = dest.getTarget();
            switch (srcArr.getType()) {
                case BYTE:
                    System.arraycopy(srcArr.bytes(), srcPos, destArr.bytes(), destPos, length);
                    break;
                case CHAR:
                    System.arraycopy(srcArr.chars(), srcPos, destArr.chars(), destPos, length);
                    break;
                case SHORT:
                    System.arraycopy(srcArr.shorts(), srcPos, destArr.shorts(), destPos, length);
                    break;
                case INT:
                    System.arraycopy(srcArr.ints(), srcPos, destArr.ints(), destPos, length);
                    break;
                case FLOAT:
                    System.arraycopy(srcArr.floats(), srcPos, destArr.floats(), destPos, length);
                    break;
                case DOUBLE:
                    System.arraycopy(srcArr.doubles(), srcPos, destArr.doubles(), destPos, length);
                    break;
                case LONG:
                    System.arraycopy(srcArr.longs(), srcPos, destArr.longs(), destPos, length);
                    break;
                case REFERENCE:
                    System.arraycopy(srcArr.refs(), srcPos, destArr.refs(), destPos, length);
                    break;
            }
        }
    }
}
