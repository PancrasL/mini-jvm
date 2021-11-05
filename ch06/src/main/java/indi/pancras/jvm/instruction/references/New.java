package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;

public class New extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xbb;
    }

    @Override
    public String getOpName() {
        return "new";
    }

    @Override
    public void execute(Frame frame) {
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        JClass clazz = pool.getClassRef(index).getResolvedClass();
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError();
        }
        JObject obj = new JObject(clazz, clazz.getInstanceSlotCount());
        Reference ref = new Reference(obj);
        frame.getOperandStack().pushRef(ref);
    }
}
