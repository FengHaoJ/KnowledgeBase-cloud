package com.kb.common.exception;

public class UserNotLoginException extends BaseException{
    public UserNotLoginException() {
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }
}
