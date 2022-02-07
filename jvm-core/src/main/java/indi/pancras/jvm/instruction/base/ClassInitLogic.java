package indi.pancras.jvm.instruction.base;

import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;

public class ClassInitLogic {
    public static void initClass(JThread thread, JClass clazz) {
        clazz.setInitStarted(true);
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(JThread thread, JClass clazz) {
        Method clinit = clazz.getClinitMethod();
        if (clinit != null) {
            // exec <clinit>
            Frame frame = new Frame(thread, clinit);
            thread.pushFrame(frame);
        }
    }

    private static void initSuperClass(JThread thread, JClass clazz) {
        if (!clazz.isInterface()) {
            // java.lang.Object的父类为null
            JClass superClass = clazz.getSuperClass();
            if (superClass != null && !superClass.isInitStarted()) {
                initClass(thread, superClass);
            }
        }
    }
}
