package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLoteRequestDTO(
    @NotNull @Min(1) int quantidade,
    @NotNull @Enumerated(EnumType.STRING) UnidadeMedida unidadeMedida,
    String descricao,
    @NotBlank @NotNull String fornecedor
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
