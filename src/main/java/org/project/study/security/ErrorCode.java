package org.project.study.security;

import com.fasterxml.jackson.annotation.JsonValue;


public enum ErrorCode {

    GLOBAL(2),
    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11),
    ENCRYPT_ERROR(15);

    private int errorCode;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
