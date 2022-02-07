package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.symbolref.InterfaceMethodRef;
import indi.pancras.jvm.instruction.base.MethodInvokeLogic;
import indi.pancras.jvm.utils.LookupUtil;

public class Invokeinterface implements Instruction {
    private int index;

    @Override
    public int getOpCode() {
        return 0xb9;
    }

    @Override
    public String getOpName() {
        return "invokeinterface";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.read16();
        reader.read8();// slot count
        reader.read8();// must be 0
    }

    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        InterfaceMethodRef methodRef = pool.getInterfaceMethodRef(index);
        Method method = methodRef.resolvedMethod();
        if (method.isStatic() || method.isPrivate()) {
            throw new IncompatibleClassChangeError();
        }
        // 弹出this引用
        Reference ref = frame.getOperandStack().getRefFromTop(method.getArgSlotCount() - 1);
        Method methodToBeInvoked = LookupUtil.lookupMethodInClass(ref.getTarget().getClazz(), methodRef.getMethodName(), methodRef.getDescriptor(), true);
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
