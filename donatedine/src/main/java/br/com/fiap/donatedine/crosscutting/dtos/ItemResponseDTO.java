package br.com.fiap.donatedine.crosscutting.dtos;

public record ItemResponseDTO(
    String id,
    String estoqueId, 
    String classificacaoId, 
    String nome, 
    String classificacao,
    String imagemPath
) {}

