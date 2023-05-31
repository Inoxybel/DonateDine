package br.com.fiap.donatedine.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_GS3_Lote")
public class Lote {
    
    @Id
    @JsonIgnore
    public String id;

    @Min(1)
    @NotNull
    @Column(nullable = false)
    public int quantidade;

    @Column(nullable = false)
    public UnidadeMedida unidadeMedida;

    @Column(nullable = false)
    public String descricao;

    @Column(nullable = false)
    public String fornecedor;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    public LocalDateTime dataCriacao = LocalDateTime.now();
}
