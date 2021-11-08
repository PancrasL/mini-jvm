package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.DescriptorFlag;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;
import indi.pancras.jvm.rtda.symbolref.FieldRef;
import indi.pancras.jvm.utils.SlotsUtil;

/**
 * 获取对象的实例变量值，然后放入操作数栈，需要两个操作数：
 * <p>
 * 1. 属性的常量池索引（字节码） 2. 对象引用（操作数栈）
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
    public void execute(Frame frame) {
        // 1. 获取待赋值的属性及其类变量
        JClass currentClazz = frame.getMethod().getClazz();
        FieldRef fieldRef = currentClazz.getConstantPool().getFieldRef(index);
        Field field = fieldRef.getTargetField();

        // 2. 安全验证
        // 2.1 解析后的字段不是对象实例变量，抛出异常
        if (field.isStatic()) {
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
        Slot[] slots = ref.getTarget().getFields();
        switch (descriptor.charAt(0)) {
            // 占用1个槽
            case DescriptorFlag.BOOLEAN_FLAG:
            case DescriptorFlag.BYTE_FLAG:
            case DescriptorFlag.CHAR_FLAG:
            case DescriptorFlag.SHORT_FLAG:
            case DescriptorFlag.INT_FLAG:
                operandStack.pushInt(SlotsUtil.getInt(slots, slotId));
                break;
            case DescriptorFlag.FLOAT_FLAG:
                operandStack.pushFloat(SlotsUtil.getFloat(slots, slotId));
                break;
            case DescriptorFlag.OBJECT_FLAG:
            case DescriptorFlag.ARRAY_FLAG:
                operandStack.pushRef(SlotsUtil.getRef(slots, slotId));
                break;
            // 占用2个槽
            case DescriptorFlag.LONG_FLAG:
                operandStack.pushLong(SlotsUtil.getLong(slots, slotId));
                break;
            case DescriptorFlag.DOUBLE_FLAG:
                operandStack.pushDouble(SlotsUtil.getDouble(slots, slotId));
                break;
            default:
                throw new VerifyError("Illegal descriptor: " + descriptor);
        }
    }
}
