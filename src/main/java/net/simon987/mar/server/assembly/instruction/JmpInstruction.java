package net.simon987.mar.server.assembly.instruction;

import net.simon987.mar.server.assembly.CPU;
import net.simon987.mar.server.assembly.Instruction;
import net.simon987.mar.server.assembly.Status;
import net.simon987.mar.server.assembly.Target;

public class JmpInstruction extends Instruction {

    public static final int OPCODE = 10;

    private final CPU cpu;

    public JmpInstruction(CPU cpu) {
        super("jmp", OPCODE);

        this.cpu = cpu;
    }

    @Override
    public Status execute(Target src, int srcIndex, Status status) {

        cpu.setIp((char) src.get(srcIndex));
        return status;
    }

    @Override
    public Status execute(int src, Status status) {

        cpu.setIp((char) src);
        return status;
    }
}