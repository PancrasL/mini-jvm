package indi.pancras.jvm.instruction.references;

import java.util.List;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Slot;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.symbolref.FieldRef;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 给类的某个静态变量赋值，包含两个操作数：
 * <p>
 * 第一个是uint16，来自字节码，第二个是要赋值给静态变量的值，从操作数栈弹出
 * </p>
 */
public class Putstatic extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb3;
    }

    @Override
    public String getOpName() {
        return "putstatic";
    }

    @Override
    public void execute(Frame frame) {
        // 1. 获取当前方法、当前类、当前常量池、需要被赋值的字段和其所属的类
        Method currentMethod = frame.getMethod();
        JClass currentClazz = currentMethod.getClazz();
        RuntimeConstantPool currentPool = currentClazz.getConstantPool();

        FieldRef fieldRef = currentPool.getFieldRef(index);
        Field field = fieldRef.getTargetField();
        JClass clazz = field.getClazz();

        // 2. 安全验证
        // 2.1 解析后的字段不是静态字段，抛出异常
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        // 2.2 解析后的字段是final字段，则只能在类初始化方法 <clinit> 中给它赋值
        if (field.isFinal()) {
            if (!(currentClazz == clazz && currentMethod.getName().equals("<clinit>"))) {
                throw new IllegalAccessError();
            }
        }

        // 3. 执行赋值操作
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        List<Slot> staticSlots = clazz.getStaticSlots();
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            // 占用1个槽
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
            case 'F':
            case 'L':
            case '[':
                staticSlots.set(slotId, operandStack.popSlot());
                break;
            // 占用两个槽
            case 'J':
            case 'D':
                // low字节
                staticSlots.set(slotId + 1, operandStack.popSlot());
                // high字节
                staticSlots.set(slotId, operandStack.popSlot());
                break;
            default:
                throw new RuntimeException("Illegal descriptor: " + descriptor);
        }
    }
}
