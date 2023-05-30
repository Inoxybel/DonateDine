package br.com.fiap.donatedine.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Lote {
    
    @Id
    @JsonIgnore
    public String id;

    @Min(1)
    @NotNull
    @Column(nullable = false)
    public int qtd;

    @Column(nullable = false)
    public UnidadeMedida unidadeMedida;

    @Column(nullable = false)
    public String descricao;

    @Column(nullable = false)
    public String fornecedor;

    @NotNull
    @Column(nullable = false)
    public Date dataCriacao;
}
