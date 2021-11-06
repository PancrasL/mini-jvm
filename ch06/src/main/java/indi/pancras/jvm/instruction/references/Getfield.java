package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.symbolref.FieldRef;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 获取对象的实例变量值，然后放入操作数栈，需要两个操作数：
 * <p>
 * 1. int16索引（来自字节码） 2. 对象引用（来自操作数栈）
 * </p>
 */
public class Getfield extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb4;
    }

    @Override
    public String getOpName() {
        return "getfield";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        throw new RuntimeException("Not implement: " + getOpName());
    }

    @Override
    public void execute(Frame frame) {
        // 1. 获取待赋值的属性及其类变量
        Method currentMethod = frame.getMethod();
        JClass currentClazz = currentMethod.getClazz();
        RuntimeConstantPool currentPool = currentClazz.getConstantPool();
        FieldRef fieldRef = currentPool.getFieldRef(index);
        Field field = fieldRef.getTargetField();

        // 2. 安全验证
        // 2.1 解析后的字段不是静态字段，抛出异常
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        // 3. 执行赋值操作
        OperandStack operandStack = frame.getOperandStack();
        Reference ref = operandStack.popRef();
        if (ref.targetIsNull()) {
            throw new NullPointerException();
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        switch (descriptor.charAt(0)) {
            // 占用1个槽
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                operandStack.pushInt(ref.getTarget().getInt(slotId));
                break;
            case 'F':
                operandStack.pushFloat(ref.getTarget().getFloat(slotId));
                break;
            case 'L':
            case '[':
                operandStack.pushRef(ref.getTarget().getRef(slotId));
                break;
            // 占用2个槽
            case 'J':
                operandStack.pushLong(ref.getTarget().getLong(slotId));
                break;
            case 'D':
                operandStack.pushDouble(ref.getTarget().getDouble(slotId));
                break;
            default:
                throw new RuntimeException("Illegal descriptor: " + descriptor);
        }
    }
}
