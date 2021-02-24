package com.z1s1c1.exception;

//用来表示当用户的年龄有异常，抛出AgeException
public class AgeException extends MyUserException{
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}
