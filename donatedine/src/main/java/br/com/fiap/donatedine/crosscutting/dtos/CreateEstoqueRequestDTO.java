package br.com.fiap.donatedine.crosscutting.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateEstoqueRequestDTO(@JsonProperty("id_lote") String idLote) {}

