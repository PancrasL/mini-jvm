package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.heap.Field;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.heap.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.symbolref.FieldRef;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 给示例变量赋值，包含3个操作数：
 * <p>
 * 1. 属性的常量池索引(从字节码文件获取) 2. 变量值(从操作数栈获取) 3. 对象引用(从操作数栈获取)
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
        RuntimeConstantPool currentPool = currentClazz.getConstantPool();
        FieldRef fieldRef = currentPool.getFieldRef(index);
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
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
            case 'F': {
                int val = operandStack.popInt();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                ref.getTarget().setInt(slotId, val);
            }
            break;
            case 'L':
            case '[': {
                Reference val = operandStack.popRef();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                ref.getTarget().setRef(slotId, val);
            }
            break;
            // 占用2个槽
            case 'J': {
                long val = operandStack.popLong();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                ref.getTarget().setLong(slotId, val);
            }
            break;
            case 'D': {
                double val = operandStack.popDouble();
                Reference ref = operandStack.popRef();
                if (ref.targetIsNull()) {
                    throw new NullPointerException();
                }
                ref.getTarget().setDouble(slotId, val);
            }
            break;
            default:
                throw new RuntimeException("Illegal descriptor: " + descriptor);
        }
    }
}
