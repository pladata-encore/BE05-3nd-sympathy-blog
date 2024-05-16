package com.example.post.exception;

public class ImageNotFoundException extends IllegalArgumentException {
    public ImageNotFoundException() {
        super("해당 이미지가 존재하지 않습니다.");
    }
}
