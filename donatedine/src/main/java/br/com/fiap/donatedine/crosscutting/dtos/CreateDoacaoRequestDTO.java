package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateDoacaoRequestDTO(
    String descricao,
    String cnpjDestinatario
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
