package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.instruction.base.MethodInvokeLogic;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;
import indi.pancras.jvm.rtda.symbolref.MethodRef;
import indi.pancras.jvm.utils.LookupUtil;

public class Invokevirtual extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb6;
    }

    @Override
    public String getOpName() {
        return "invokevirtual";
    }

    @Override
    public void execute(Frame frame) {
        // 1. 获取当前栈帧所属的类和方法符号引用
        JClass currentClazz = frame.getMethod().getClazz();
        MethodRef methodRef = currentClazz.getConstantPool().getMethodRef(index);

        // 2. 获取待触发的方法
        Method method = methodRef.resolvedMethod();
        // 弹出this引用
        Reference ref = frame.getOperandStack().getRefFromTop(method.getArgSlotCount() - 1);
        if (ref == null || ref.targetIsNull()) {
            //hack
            if (methodRef.getMethodName().equals("println")) {
                println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
            throw new NullPointerException("ref is null");
        }
        Method methodToBeInvoked = LookupUtil.lookupMethodInClass(currentClazz, methodRef.getMethodName(), methodRef.getDescriptor(), true);
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

    private void println(OperandStack stack, String descriptor) {
        switch (descriptor) {
            case "(Z)V":
                System.out.println(stack.popInt() != 0);
                break;
            case "(C)V":
                System.out.println((char) stack.popInt());
                break;
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.out.println(stack.popInt());
                break;
            case "(F)V":
                System.out.println(stack.popFloat());
                break;
            case "(J)V":
                System.out.println(stack.popLong());
                break;
            case "(D)V":
                System.out.println(stack.popDouble());
                break;
            case "(Ljava/lang/String;)V":
                JObject jStr = stack.popRef().getTarget();
                byte[] values = jStr.getRefVar("value", "[C").getTarget().bytes();
                String s = new String(values);
                System.out.println(s);
                break;
            default:
                throw new RuntimeException(descriptor);
        }
        stack.popRef();
    }
}
