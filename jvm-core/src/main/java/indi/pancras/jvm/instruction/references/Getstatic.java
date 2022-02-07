package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.instruction.base.ClassInitLogic;
import indi.pancras.jvm.rtda.DescriptorFlag;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;
import indi.pancras.jvm.rtda.symbolref.FieldRef;
import indi.pancras.jvm.utils.SlotsUtil;

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
        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            ClassInitLogic.initClass(frame.getThread(), clazz);
            return;
        }

        // 2. 安全验证
        // 2.1 解析后的字段不是静态字段，抛出异常
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        // 3. 执行赋值操作
        OperandStack operandStack = frame.getOperandStack();
        String descriptor = field.getDescriptor();
        Slot[] slots = clazz.getStaticFields();
        int slotId = field.getSlotId();
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
