package indi.pancras.jvm.utils;

import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;

public class LookupUtil {

    /**
     * 在类中查找目标方法
     *
     * @param clazz      查找类
     * @param methodName 待查找的方法名
     * @param descriptor 待查找的方法描述符
     * @param recur      是否在父类中进行递归查找
     * @return method or null
     */
    public static Method lookupMethodInClass(JClass clazz, String methodName, String descriptor, Boolean recur) {
        if (clazz == null) {
            return null;
        }
        for (Method method : clazz.getMethods()) {
            if (method.getMethodName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }

        return recur ? lookupMethodInClass(clazz.getSuperClass(), methodName, descriptor, true) : null;
    }

    /**
     * 在类中查找目标静态方法
     *
     * @param clazz      查找类
     * @param methodName 待查找的方法名
     * @param descriptor 待查找的方法的描述符
     * @return method or null
     */
    public static Method lookupStaticMethodInClass(JClass clazz, String methodName, String descriptor) {
        for (Method method : clazz.getMethods()) {
            if (method.isStatic()) {
                if (method.getMethodName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * 在类iface的接口中查找方法
     *
     * @param iface      接口
     * @param methodName 待查找的方法名
     * @param descriptor 待查找的方法的描述符
     * @param recur      是否在父接口中查找
     * @return method or null
     */
    public static Method lookupMethodInInterface(JClass iface, String methodName, String descriptor, Boolean recur) {
        // 首先在当前接口的方法中查找
        for (Method method : iface.getMethods()) {
            if (method.getMethodName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }

        // 如果递归查找，则在当前接口的父接口中查找
        if (recur) {
            for (JClass i : iface.getInterfaces()) {
                lookupMethodInInterface(i, methodName, descriptor, true);
            }
        }
        return null;
    }
}
