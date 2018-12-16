package com.example.demo.controllers;

import com.example.demo.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LogManager.getLogger();

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public final ModelAndView handleNotFound(Exception ex) {
        String message = ex.getMessage();

        return this.handleNotFoundException(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public final ModelAndView handleNumberFormat(Exception ex) {
        String message = ex.getMessage();

        return this.handleNumberFormatException(message);
    }

    /** Customize the response for NotFoundException. */
    private ModelAndView handleNotFoundException(String message) {
        log.error("Handle Not Found Exception");
        log.error(message);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.setViewName("errors/404");

        return mav;
    }

    /** Customize the response for BadRequest. */
    private ModelAndView handleNumberFormatException(String message) {
        log.error("Handle Number Format Exception");
        log.error(message);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.setViewName("errors/400");

        return mav;
    }
}