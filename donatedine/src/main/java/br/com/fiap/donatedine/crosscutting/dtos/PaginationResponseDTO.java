package br.com.fiap.donatedine.crosscutting.dtos;

import java.util.List;

public record PaginationResponseDTO<T>(
    List<T> content,
    int number,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last
){}
