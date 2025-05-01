package org.skypro.counter.model.controller;

import org.skypro.counter.model.exception.NoSuchProductException;
import org.skypro.counter.model.search.ShopError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductHandler(NoSuchProductException e) {
        return ResponseEntity.status(404).body(new ShopError("PRODUCT_NOT_FOUND",e.getMessage()));
    }
}
