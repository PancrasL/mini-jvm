package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.instruction.base.ClassInitLogic;
import indi.pancras.jvm.rtda.DescriptorFlag;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;
import indi.pancras.jvm.rtda.symbolref.FieldRef;
import indi.pancras.jvm.utils.SlotsUtil;

/**
 * 给类的某个静态变量赋值，包含两个操作数：
 * <p>
 * 1. 属性的常量池索引（字节码） 2. 变量值（操作数栈）
 * </p>
 */
public class Putstatic extends BaseIndex16 {
    private static final String CLINIT = "<clinit>";

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
        // 2.2 解析后的字段是final字段，则只能在类初始化方法 <clinit> 中给它赋值
        if (field.isFinal()) {
            if (!(currentClazz == clazz && currentMethod.getMethodName().equals(CLINIT))) {
                throw new IllegalAccessError();
            }
        }

        // 3. 执行赋值操作
        String descriptor = field.getDescriptor();
        Slot[] slots = clazz.getStaticFields();
        int slotId = field.getSlotId();
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            // 占用1个槽
            case DescriptorFlag.BOOLEAN_FLAG:
            case DescriptorFlag.BYTE_FLAG:
            case DescriptorFlag.CHAR_FLAG:
            case DescriptorFlag.SHORT_FLAG:
            case DescriptorFlag.INT_FLAG:
                SlotsUtil.setInt(slots, slotId, operandStack.popInt());
                break;
            case DescriptorFlag.FLOAT_FLAG:
                SlotsUtil.setFloat(slots, slotId, operandStack.popFloat());
                break;
            case DescriptorFlag.OBJECT_FLAG:
            case DescriptorFlag.ARRAY_FLAG:
                SlotsUtil.setRef(slots, slotId, operandStack.popRef());
                break;
            // 占用2个槽
            case DescriptorFlag.LONG_FLAG:
                SlotsUtil.setLong(slots, slotId, operandStack.popLong());
                break;
            case DescriptorFlag.DOUBLE_FLAG:
                SlotsUtil.setDouble(slots, slotId, operandStack.popDouble());
                break;
            default:
                throw new VerifyError("Illegal descriptor: " + descriptor);
        }
    }
}
