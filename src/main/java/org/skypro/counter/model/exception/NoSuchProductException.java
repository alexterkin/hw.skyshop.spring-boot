package org.skypro.counter.model.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Товар не найден");
    }
}
