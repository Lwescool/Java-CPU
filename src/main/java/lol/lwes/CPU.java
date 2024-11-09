package lol.lwes;

import java.util.Arrays;

public class CPU {
    private static final int memorySize = 1024;
    private static final int regs = 8;

    private int[] registers;
    private int[] memory;
    private int pc;
    private boolean halted;

    public CPU() {
        registers = new int[regs];
        memory = new int[memorySize];
        pc = 0;
        halted = false;
    }

    public void loadProgram(int[] program) {
        System.arraycopy(program, 0, memory, 0, program.length);
    }

    public void run() {
        while (!halted) {
            int encodedInstr = memory[pc];
            Instruction instr = Instruction.decode(encodedInstr);
            execute(instr, encodedInstr);
        }
    }

    private void execute(Instruction instr, int encodedInstr) {
        int arg1 = Instruction.getArg1(encodedInstr);
        int arg2 = Instruction.getArg2(encodedInstr);
        int arg3 = Instruction.getArg3(encodedInstr);

        switch (instr) {
            case ADD:
                registers[arg1] = registers[arg2] + registers[arg3];
                pc++;
                break;

            case SUB:
                registers[arg1] = registers[arg2] - registers[arg3];
                pc++;
                break;

            case LOAD_IMM:
                registers[arg1] = arg2;
                pc++;
                break;

            case LOAD_MEM:
                registers[arg1] = memory[arg2];
                pc++;
                break;

            case STORE_MEM:
                memory[arg2] = registers[arg1];
                pc++;
                break;

            case JUMP_IF_ZERO:
                pc = registers[arg1] == 0 ? arg2 : pc + 1;
                break;

            case PRINT:
                print(arg1, arg2);
                pc++;
                break;

            case HALT:
                halted = true;
                break;

            default:
                throw new IllegalArgumentException("unknown instruction");
        }
    }

    private void print(int startAddress, int length) {
        StringBuilder msg = new StringBuilder();
        for (int i = startAddress; i < startAddress + length; i++) {
            msg.append((char) memory[i]);
        }
        System.out.println("opt: " + msg);
    }

    public void printState() {
        System.out.println("Registers: " + Arrays.toString(registers));
        System.out.println("Memory: " + Arrays.toString(Arrays.copyOf(memory, 10)));
        System.out.println("PC: " + pc);
    }


}

