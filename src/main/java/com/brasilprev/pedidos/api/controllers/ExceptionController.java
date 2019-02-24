package com.brasilprev.pedidos.api.controllers;

import com.brasilprev.pedidos.utils.ExcecaoDeNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController{

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<?> handleException(Exception ex) {

        if(ex instanceof ExcecaoDeNegocio)
            return ResponseEntity.badRequest().body(ex.getMessage());

        return ResponseEntity.status(500).body("Caracas! Aconteceu algo que nem eu sei falar o que é!");
    }
}
