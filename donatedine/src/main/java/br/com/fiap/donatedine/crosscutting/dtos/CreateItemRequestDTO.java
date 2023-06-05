package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateItemRequestDTO(
    @NotBlank @NotNull String nome,
    @NotBlank @NotNull String pathImagem,
    @NotBlank @NotNull String classificacao
) {
    @JsonCreator
    public CreateItemRequestDTO(
        String nome,
        @JsonProperty("path_imagem") String pathImagem,
        String classificacao
    ){
        this.nome = nome;
        this.pathImagem = pathImagem;
        this.classificacao = classificacao;
    }

}
