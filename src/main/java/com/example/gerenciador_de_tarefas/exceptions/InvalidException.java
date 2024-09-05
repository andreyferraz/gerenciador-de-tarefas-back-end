package com.example.gerenciador_de_tarefas.exceptions;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
