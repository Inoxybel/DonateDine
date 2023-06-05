package br.com.fiap.donatedine.crosscutting.dtos;

import java.time.LocalDateTime;

public record EstoqueResponseDTO(
    String id, 
    String loteId,
    String doacaoId, 
    LocalDateTime dataCriacao
) {}

