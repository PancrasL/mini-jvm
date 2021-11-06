package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.symbolref.FieldRef;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 取出类的某个静态变量赋值，放入栈顶，需要一个操作数：
 * <p>
 * 1. 属性的常量池索引（字节码）
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
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            // 占用1个槽
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                operandStack.pushInt(clazz.getInt(slotId));
                break;
            case 'F':
                operandStack.pushFloat(clazz.getFloat(slotId));
                break;
            case 'L':
            case '[':
                operandStack.pushRef(clazz.getRef(slotId));
                break;
            // 占用2个槽
            case 'J':
                operandStack.pushLong(clazz.getLong(slotId));
                break;
            case 'D':
                operandStack.pushDouble(clazz.getDouble(slotId));
                break;
            default:
                throw new RuntimeException("Illegal descriptor: " + descriptor);
        }
    }
}
