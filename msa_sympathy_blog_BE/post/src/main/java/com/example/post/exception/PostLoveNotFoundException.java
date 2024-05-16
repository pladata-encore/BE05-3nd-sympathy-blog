package com.example.post.exception;

public class PostLoveNotFoundException extends IllegalArgumentException {
    public PostLoveNotFoundException() {
        super("좋아요를 가져올 수 없습니다.");
    }
}
