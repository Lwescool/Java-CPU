package lol.lwes;

public enum Instruction {
    ADD, SUB, LOAD_IMM, LOAD_MEM, STORE_MEM, JUMP_IF_ZERO, HALT, PRINT;

    public static Instruction decode(int encoded) {
        int opcode = (encoded >> 24) & 0xFF;
        return Instruction.values()[opcode];
    }

    public static int encode(Instruction instr, int arg1, int arg2, int arg3) {
        return (instr.ordinal() << 24) | (arg1 << 16) | (arg2 << 8) | arg3;
    }

    public static int getArg1(int encoded) { return (encoded >> 16) & 0xFF; }
    public static int getArg2(int encoded) { return (encoded >> 8) & 0xFF; }
    public static int getArg3(int encoded) { return encoded & 0xFF; }
}
