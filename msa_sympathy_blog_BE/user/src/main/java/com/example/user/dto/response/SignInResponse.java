package com.example.user.dto.response;

public record SignInResponse(
        String token,
        String tokenType
) {
    public static SignInResponse from(String token) {
        return new SignInResponse(token, "Bearer");
    }
}
