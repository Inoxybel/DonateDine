package br.com.fiap.donatedine.crosscutting.dtos;

import java.time.LocalDateTime;

public record DoacaoResponseDTO(
    String id, 
    String estoqueId, 
    String descricao, 
    String cnpjDestinatario, 
    LocalDateTime dataCriacao
) {}

