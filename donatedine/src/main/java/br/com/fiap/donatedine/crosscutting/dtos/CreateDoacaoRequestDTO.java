package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDoacaoRequestDTO(
    String descricao,
    @NotBlank @NotNull String cnpjDestinatario
) {
    @JsonCreator
    public CreateDoacaoRequestDTO(
        String descricao,
        @JsonProperty("cnpj_destinatario") String cnpjDestinatario
    ) {
        this.descricao = descricao;
        this.cnpjDestinatario = cnpjDestinatario;
    }
}
