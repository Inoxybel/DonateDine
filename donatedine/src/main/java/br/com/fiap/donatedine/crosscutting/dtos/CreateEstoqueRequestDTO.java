package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEstoqueRequestDTO(
    @NotBlank @NotNull @JsonProperty("id_lote") String idLote
) {}

