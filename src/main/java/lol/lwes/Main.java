package lol.lwes;

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU();

        int[] program = {
                Instruction.encode(Instruction.LOAD_IMM, 0, 104, 0), // h
                Instruction.encode(Instruction.STORE_MEM, 0, 0, 0),
                Instruction.encode(Instruction.LOAD_IMM, 0, 101, 0), // e
                Instruction.encode(Instruction.STORE_MEM, 0, 1, 0),
                Instruction.encode(Instruction.LOAD_IMM, 0, 108, 0), // l
                Instruction.encode(Instruction.STORE_MEM, 0, 2, 0),
                Instruction.encode(Instruction.LOAD_IMM, 0, 108, 0), // l
                Instruction.encode(Instruction.STORE_MEM, 0, 3, 0),
                Instruction.encode(Instruction.LOAD_IMM, 0, 111, 0), // o
                Instruction.encode(Instruction.STORE_MEM, 0, 4, 0),

                Instruction.encode(Instruction.PRINT, 0, 5, 0),

                Instruction.encode(Instruction.HALT, 0, 0, 0)
        };

        cpu.loadProgram(program);
        cpu.run();
        cpu.printState();
    }
}