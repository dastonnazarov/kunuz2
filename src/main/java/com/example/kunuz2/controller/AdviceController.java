package com.example.kunuz2.controller;

import com.example.kunuz2.exps.AppBadRequestException;
import com.example.kunuz2.exps.ItemNotFoundException;

import com.example.kunuz2.exps.MethodNotAllowedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({AppBadRequestException.class,
            ItemNotFoundException.class,
            MethodNotAllowedException.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
