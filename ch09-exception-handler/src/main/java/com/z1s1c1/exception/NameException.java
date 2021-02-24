package com.z1s1c1.exception;

//用来表示当用户的姓名有异常，抛出NameException
public class NameException extends MyUserException{
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
