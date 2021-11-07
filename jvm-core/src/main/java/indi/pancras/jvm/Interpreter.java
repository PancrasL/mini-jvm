package indi.pancras.jvm;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.instruction.InstructionFactory;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.heap.Method;

/**
 * 解释器，执行某个方法
 */
public class Interpreter {
    public static void execute(Method method) {
        if (method == null) {
            throw new IllegalArgumentException("Method is null");
        }
        JThread thread = new JThread();
        Frame frame = new Frame(thread, method);
        thread.pushFrame(frame);

        executeCode(thread);
    }

    private static void executeCode(JThread thread) {
        Frame frame = thread.popFrame();
        byte[] code = frame.getMethod().getCode();
        BytecodeReader reader = new BytecodeReader(code, frame.getNextPc());
        int opCode;
        do {
            int pc = frame.getNextPc();
            thread.setPc(pc);

            // decode
            reader.reset(code, pc);
            opCode = reader.read8();
            Instruction instruction = InstructionFactory.getByOpcode(opCode);
            instruction.fetchOperands(reader);
            System.out.printf("pc: %d, opName: %s%n", frame.getNextPc(), instruction.getOpName());
            frame.setNextPc(reader.getPc());

            // execute
            instruction.execute(frame);
            System.out.println(frame);
        } while (opCode != 0xb1);
    }
}
