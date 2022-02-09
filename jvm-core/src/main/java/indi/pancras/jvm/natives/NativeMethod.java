package indi.pancras.jvm.natives;

import indi.pancras.jvm.rtda.stack.Frame;

public interface NativeMethod {
    void invokeMethod(Frame frame);
}
