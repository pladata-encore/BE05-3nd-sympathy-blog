package com.example.post.exception;

public class NoLoverException extends IllegalArgumentException {
    public NoLoverException() {
        super("공감 해주세요!");
    }
}
