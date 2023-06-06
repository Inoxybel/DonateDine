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
@Table(name = "T_LOTES")
public class Lote {
    
    @Id
    @JsonIgnore
    @Column(name = "PK_ID", columnDefinition = "CHAR(36)")
    public String id;

    @Min(1)
    @NotNull
    @Column(nullable = false, name = "QUANTIDADE")
    public int quantidade;

    @Column(nullable = false, name = "UNIDADE_MEDIDA")
    public UnidadeMedida unidadeMedida;

    @Column(nullable = true, name = "DESCRICAO")
    public String descricao;

    @Column(nullable = false, name = "FORNECEDOR")
    public String fornecedor;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP", nullable = false, name = "DATA_CRIACAO")
    public LocalDateTime dataCriacao = LocalDateTime.now();
}
