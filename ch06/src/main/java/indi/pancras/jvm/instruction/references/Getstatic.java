package indi.pancras.jvm.instruction.references;

import java.util.List;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Slot;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.symbolref.FieldRef;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 取出类的某个静态变量赋值，放入栈顶，需要一个操作数：
 * <p>
 * 来自字节码的uint16常量池索引
 * </p>
 */
public class Getstatic extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb2;
    }

    @Override
    public String getOpName() {
        return "getstatic";
    }

    @Override
    public void execute(Frame frame) {
        // 1. 根据常量池索引获取待访问的字段及其所属的类
        RuntimeConstantPool currentPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = currentPool.getFieldRef(index);
        Field field = fieldRef.getTargetField();
        JClass clazz = field.getClazz();

        // 2. 安全验证
        // 2.1 解析后的字段不是静态字段，抛出异常
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
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
                operandStack.pushSlot(staticSlots.get(slotId));
                break;
            // 占用两个槽
            case 'J':
            case 'D':
                // high字节
                operandStack.pushSlot(staticSlots.get(slotId));
                // low字节
                operandStack.pushSlot(staticSlots.get(slotId + 1));
                break;
            default:
                throw new RuntimeException("Illegal descriptor: " + descriptor);
        }
    }
}
