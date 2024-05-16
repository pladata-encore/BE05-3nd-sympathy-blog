package com.example.post.exception;

public class CategoryNotFoundException extends IllegalArgumentException {
    public CategoryNotFoundException() {
        super("해당 카테고리가 존재하지 않습니다.");
    }
}
