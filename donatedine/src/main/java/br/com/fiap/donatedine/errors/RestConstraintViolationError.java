package br.com.fiap.donatedine.errors;

public record RestConstraintViolationError(
        int code,
        Object field,
        String message
) {}