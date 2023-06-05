package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateItemRequestDTO(
    String nome,
    String pathImagem,
    String classificacao
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
