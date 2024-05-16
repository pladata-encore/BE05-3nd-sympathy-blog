package com.example.post.controller;

import com.example.post.exception.*;
import com.example.post.global.domain.entity.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(UserNotFoundException.class)
    public String handlerExistingUserException(UserNotFoundException e) {
        log.debug(e.getMessage());
        return e.getMessage();
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public String handlerCategoryNotFoundException(CategoryNotFoundException e) {
        log.debug(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(NoLoverException.class)
    public String handlerNoLoverException(NoLoverException e) {
        log.debug(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(PostLoveNotFoundException.class)
    public String handlerPostLoveNotFoundException(PostLoveNotFoundException e) {
        log.debug(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(PostNotFoundException.class)
    public String handlerPostNotFoundException(PostNotFoundException e) {
        log.debug(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public String handlerImageNotFoundException(ImageNotFoundException e) {
        log.debug(e.getMessage());
        return e.getMessage();
    }

}
