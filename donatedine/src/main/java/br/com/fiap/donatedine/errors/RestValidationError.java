package br.com.fiap.donatedine.errors;

public record RestValidationError(
        Integer code,
        String field,
        String message
) {}
