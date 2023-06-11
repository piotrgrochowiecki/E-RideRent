package com.piotgrochowiecki.eriderent.controller;

import com.piotgrochowiecki.eriderent.exception.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@CommonsLog
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NoCarFoundException.class)
    public String noCarFoundExceptionHandler() {
        log.info("NoCarFoundException has been thrown!");
        return "/noCarFoundEx";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CarDeletionException.class)
    public String carDeletionExceptionHandler() {
        log.info("CarDeletionException has been thrown!");
        return "/carDeletionEx";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoRecordedPositionException.class)
    public String noRecordedPositionExceptionHandler() {
        log.info("NoRecordedPositionException has been thrown!");
        return "noRecordedPositionEx";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyExistsException.class)
    public String emailAlreadyExistsExceptionHandler() {
        log.info("EmailAlreadyExistsException has been thrown!");
        return "/emailAlreadyExistsEx";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoUserFoundException.class)
    public String noUserFoundExceptionHandler() {
        log.info("NoUserFoundException has been thrown!");
        return "/noUserFoundEx";
    }

}
