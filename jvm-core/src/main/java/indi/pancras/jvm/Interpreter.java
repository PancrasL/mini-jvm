package indi.pancras.jvm;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.instruction.InstructionFactory;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 解释器，执行某个方法
 */
public class Interpreter {
    public static void interpret(Method method, boolean logInst) {
        if (method == null) {
            throw new IllegalArgumentException("Method is null");
        }
        JThread thread = new JThread();
        Frame frame = new Frame(thread, method);
        thread.pushFrame(frame);

        executeCode(thread, logInst);
    }

    private static void executeCode(JThread thread, boolean logInst) {
        BytecodeReader reader = new BytecodeReader();
        while (!thread.isStackEmpty()) {
            Frame frame = thread.topFrame();
            int pc = frame.getNextPc();
            thread.setPc(pc);

            // decode
            reader.reset(frame.getMethod().getCode(), pc);
            int opCode = reader.read8();
            Instruction instruction = InstructionFactory.getByOpcode(opCode);
            instruction.fetchOperands(reader);
            frame.setNextPc(reader.getPc());
            // log
            if (logInst) {
                logInstruction(frame, instruction);
            }
            // execute
            instruction.execute(frame);
            //logOperandStack(frame);
        }
    }

    private static void logInstruction(Frame frame, Instruction inst) {
        Method method = frame.getMethod();
        String className = method.getClazz().getClassName();
        String methodName = method.getMethodName();
        int pc = frame.getThread().getPc();
        String msg = String.format("%s.%s # %2d %s\n", className, methodName, pc, inst.getOpName());
        System.out.println(msg);
    }

    private static void logOperandStack(Frame frame){
        OperandStack operandStack = frame.getOperandStack();
        System.out.println(operandStack);
    }
}
