package com.example.gerenciador_de_tarefas.exceptions;

public class NotFoundException extends RuntimeException  {
    public NotFoundException(String message) {
        super(message);
    }
}
