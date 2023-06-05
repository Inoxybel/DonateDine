package br.com.fiap.donatedine.crosscutting.dtos;

import java.time.LocalDateTime;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;

public record LoteResponseDTO(
    String id, 
    int quantidade, 
    UnidadeMedida unidadeMedida, 
    String descricao,
    String fornecedor, 
    LocalDateTime dataCriacao
) {}

