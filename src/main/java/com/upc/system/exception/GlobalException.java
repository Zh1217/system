package com.upc.system.exception;

import com.upc.system.config.CodeMsg;


public class GlobalException extends RuntimeException {

    private static final long SERIAL_VERSION_ID = 1L;

    private CodeMsg codeMsg;

    /**
     * 带参数的构造函数
     */
    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public static long getSerialVersionId() {
        return SERIAL_VERSION_ID;
    }


    CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

}
