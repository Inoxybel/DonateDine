package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;

public record CreateLoteRequestDTO(
    int quantidade,
    UnidadeMedida unidadeMedida,
    String descricao,
    String fornecedor
) {
    public CreateLoteRequestDTO(
        int quantidade,
        @JsonProperty("unidade_medida") UnidadeMedida unidadeMedida,
        String descricao,
        String fornecedor
    ) {
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
    }
}
