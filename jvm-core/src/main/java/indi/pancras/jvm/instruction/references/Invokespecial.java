package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.symbolref.MethodRef;
import indi.pancras.jvm.instruction.base.MethodInvokeLogic;
import indi.pancras.jvm.utils.LookupUtil;

// 私有方法、初始化方法（<init>(),<clinit>())
public class Invokespecial extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb7;
    }

    @Override
    public String getOpName() {
        return "invokespecial";
    }

    @Override
    public void execute(Frame frame) {
        // 1. 获取当前栈帧所属的类和方法符号引用
        JClass currentClazz = frame.getMethod().getClazz();
        MethodRef methodRef = currentClazz.getConstantPool().getMethodRef(index);

        // 2. 获取待触发的方法
        Method targetMethod = methodRef.resolvedMethod();
        JClass targetClazz = methodRef.getTargetClazz();
        // 3.1 构造方法，则声明该方法的类必须一致
        if (targetMethod.isConstructor()) {
            if (!targetMethod.getClazz().equals(targetClazz)) {
                throw new NoSuchMethodError();
            }
        }

        // this引用为null
        Reference ref = frame.getOperandStack().getRefFromTop(targetMethod.getArgSlotCount() - 1);
        if (ref.targetIsNull()) {
            throw new NullPointerException("this reference is null");
        }

        // 3.2 如果调用的是超类中的函数，但不是构造函数，且当前类的ACC_SUPER标志被设置，
        //需要一个额外的过程查找最终要调用的方法；否则前面从方法符号引用中解析出来的方法就是要调用的方法
        Method methodToBeInvoked = targetMethod;
        if (currentClazz.isSuper() &&
                targetClazz.isSuperClassOf(currentClazz) &&
                !targetMethod.isConstructor()) {
            methodToBeInvoked = LookupUtil.lookupMethodInClass(currentClazz.getSuperClass(), methodRef.getMethodName(), methodRef.getDescriptor(), true);
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
