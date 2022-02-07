package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.instruction.base.ClassInitLogic;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.stack.Frame;

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
        // 通过int16的索引从常量池中取出类符号引用，进而通过符号引用获取到类变量
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        JClass clazz = pool.getClassRef(index).getTargetClazz();
        if(!clazz.isInitStarted()){
            frame.revertNextPc();
            ClassInitLogic.initClass(frame.getThread(), clazz);
            return;
        }
        // 接口和抽象类不能实例化
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError();
        }
        // 创建一个对象
        JObject obj = new JObject(clazz);
        // 创建该对象的引用
        Reference ref = new Reference(obj);
        // 引用入栈
        frame.getOperandStack().pushRef(ref);
    }
}
