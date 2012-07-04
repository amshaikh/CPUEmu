package com.soebes.jacem2;

import com.soebes.jacem.memory.CMemoryByte;

public class OperationCodeMVI extends AbstractOperationCode {

    public OperationCodeMVI(CMemoryByte memory, RegisterSet registerSet) {
        super(memory, registerSet);

        setNumberOfBytes(1);
        setMnemonic("MVI");
    }

    @Override
    public void execute() {
        Register8Bit register = convertToRegister8Bit((getOperationCode() & 0xe8) >> 3);

        if (register == Register8Bit.M) {
            setCycles(10);
        } else {
            setCycles(7);
        }

        int value = getMemory().getMem(getRegisterSet().getProgrammCounter());
        getRegisterSet().setRegister(register, value);

        setOperators(register.name() + "," + value);
    }

}
