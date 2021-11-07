package indi.pancras.jvm.utils;

import java.util.List;

import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;

public class LookupUtil {

    public static Method lookupMethodInClass(JClass clazz, String methodName, String descriptor) {
        for (JClass c = clazz; c != null; c = clazz.getSuperClass()) {
            for (Method method : c.getMethods()) {
                if (method.getMethodName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static Method lookupStaticMethodInClass(JClass clazz, String methodName, String descriptor){
        for (Method method : clazz.getMethods()) {
            if (method.isStatic()) {
                if (method.getMethodName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static Method lookupMethodInInterfaces(List<JClass> ifaces, String methodName, String descriptor) {
        for (JClass iface : ifaces) {
            for (Method method : iface.getMethods()) {
                if (method.getMethodName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
            Method m = lookupMethodInInterfaces(iface.getInterfaces(), methodName, descriptor);
            if (m != null) {
                return m;
            }
        }
        return null;
    }
}
