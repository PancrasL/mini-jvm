package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.DescriptorFlag;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;
import indi.pancras.jvm.rtda.symbolref.FieldRef;
import indi.pancras.jvm.utils.SlotsUtil;

/**
 * 给实例变量赋值，包含3个操作数：
 * <p>
 * 1. 属性的常量池索引(字节码) 2. 变量值(操作数栈) 3. 对象引用(操作数栈)
 * </p>
 */
public class Putfield extends BaseIndex16 {
    private static final String INIT = "<init>";

    @Override
    public int getOpCode() {
        return 0xb5;
    }

    @Override
    public String getOpName() {
        return "putfield";
    }

    @Override
    public void execute(Frame frame) {
        // 1. 获取待赋值的属性及其类变量
        Method currentMethod = frame.getMethod();
        JClass currentClazz = currentMethod.getClazz();
        FieldRef fieldRef = currentClazz.getConstantPool().getFieldRef(index);
        Field field = fieldRef.getTargetField();

        // 2. 安全检查
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (!(currentClazz != field.getClazz() && currentMethod.getClazz().getClassName().equals(INIT))) {
                throw new IllegalAccessError();
            }
        }

        // 3. 执行赋值
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            // 占用1个槽
            case DescriptorFlag.BOOLEAN_FLAG:
            case DescriptorFlag.BYTE_FLAG:
            case DescriptorFlag.CHAR_FLAG:
            case DescriptorFlag.SHORT_FLAG:
            case DescriptorFlag.INT_FLAG: {
                int val = operandStack.popInt();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                SlotsUtil.setInt(ref.getTarget().getSlots(), slotId, val);
            }
            break;
            case DescriptorFlag.FLOAT_FLAG: {
                float val = operandStack.popFloat();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                SlotsUtil.setFloat(ref.getTarget().getSlots(), slotId, val);
            }
            break;
            case DescriptorFlag.OBJECT_FLAG:
            case DescriptorFlag.ARRAY_FLAG: {
                Reference val = operandStack.popRef();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                SlotsUtil.setRef(ref.getTarget().getSlots(), slotId, val);
            }
            break;
            // 占用2个槽
            case DescriptorFlag.LONG_FLAG: {
                long val = operandStack.popLong();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                SlotsUtil.setLong(ref.getTarget().getSlots(), slotId, val);
            }
            break;
            case DescriptorFlag.DOUBLE_FLAG: {
                double val = operandStack.popDouble();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                SlotsUtil.setDouble(ref.getTarget().getSlots(), slotId, val);
            }
            break;
            default:
                throw new VerifyError("Illegal descriptor: " + descriptor);
        }
    }
}
