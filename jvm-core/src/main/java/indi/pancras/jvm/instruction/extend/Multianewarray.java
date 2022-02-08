package indi.pancras.jvm.instruction.extend;

import java.util.Arrays;

import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.RuntimeConstantPool;
import indi.pancras.jvm.rtda.heap.JClass;
import indi.pancras.jvm.rtda.heap.JObject;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Multianewarray implements Instruction {
    // 多维数组类在常量池中的索引
    private int index;
    // 多维数组的维度
    private int dimensions;

    @Override
    public int getOpCode() {
        return 0xc5;
    }

    @Override
    public String getOpName() {
        return "multianewarray";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.read16();
        dimensions = reader.read8();
    }

    @Override
    public void execute(Frame frame) {
        // 从操作数栈中弹出n个整数，分别代表每一个维度的数组长度
        RuntimeConstantPool pool = frame.getMethod().getClazz().getConstantPool();
        JClass arrClass = pool.getClassRef(index).resolvedClass();

        OperandStack stack = frame.getOperandStack();
        int[] counts = popAndCheckCounts(stack, dimensions);
        JObject arr = newMultiDimensionalArray(counts, arrClass);
        stack.pushRef(new Reference(arr));
    }

    // 从操作数栈中弹出n个int值，并确保它们都大于0
    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            counts[i] = stack.popInt();
            if (counts[i] < 0) {
                throw new NegativeArraySizeException();
            }
        }
        return counts;
    }

    private JObject newMultiDimensionalArray(int[] counts, JClass arrClass) {
        int count = counts[0];
        JObject arr = arrClass.newArray(count);
        if (counts.length > 1) {
            Reference[] refs = arr.refs();
            for (int i = 0; i < refs.length; i++) {
                int[] subCounts = Arrays.copyOfRange(counts, 1, counts.length);
                JObject subArr = newMultiDimensionalArray(subCounts, arrClass.componentClass());
                refs[i] = new Reference(subArr);
            }
        }
        return arr;
    }
}
