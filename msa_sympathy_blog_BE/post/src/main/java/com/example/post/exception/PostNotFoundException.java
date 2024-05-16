package com.example.post.exception;

public class PostNotFoundException extends IllegalArgumentException {
    public PostNotFoundException() {
        super("해당 게시글이 존재하지 않습니다.");
    }
}
