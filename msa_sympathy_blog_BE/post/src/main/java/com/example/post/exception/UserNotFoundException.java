package com.example.post.exception;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
        super("해당 유저가 존재하지 않습니다.");
    }
}
