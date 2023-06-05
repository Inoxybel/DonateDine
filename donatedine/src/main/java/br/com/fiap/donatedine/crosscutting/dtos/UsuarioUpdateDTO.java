package br.com.fiap.donatedine.crosscutting.dtos;

public record UsuarioUpdateDTO
(
    String nome,
    String email,
    String senha
) {}
