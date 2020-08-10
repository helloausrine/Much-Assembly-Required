package net.simon987.mar.server.assembly.instruction;

import net.simon987.mar.server.assembly.Register;
import net.simon987.mar.server.assembly.RegisterSet;
import net.simon987.mar.server.assembly.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SetbInstructionTest {
    private final RegisterSet registers;
    private final Status status;
    private final SetccInstruction instruction;
    private final int SETCCOPCODE = SetccInstruction.SETB;

    public SetbInstructionTest() {
        registers = new RegisterSet();
        registers.put(1, new Register("R"));
        registers.clear();

        status = new Status();
        status.clear();

        instruction = new SetbInstruction();
    }

    /**
     * SETB, SETC,SETNAE  Below, Carry, Not Above or Equal     CF=1
     */
    @Test
    public void execution() {
        status.setCarryFlag(true);
        instruction.execute(registers, 1, SETCCOPCODE, status);
        assertEquals(registers.get(1), 1);

        status.setCarryFlag(false);
        instruction.execute(registers, 1, SETCCOPCODE, status);
        assertEquals(registers.get(1), 0);
    }
}