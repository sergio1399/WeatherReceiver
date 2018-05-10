package app.components.controller;

import app.components.view.ResponseView;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FromBaseControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseView handleNotFoundException(Exception ex) {
        return new ResponseView(ex.getMessage());
    }
}
