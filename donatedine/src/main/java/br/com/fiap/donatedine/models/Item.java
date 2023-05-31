package br.com.fiap.donatedine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_GS3_Item")
public class Item {
    
    @Id
    @JsonIgnore
    public String id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estoque")
    public Estoque estoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classificacao")
    public Classificacao classificacao;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    public String nome;

    @NotNull
    @Column(nullable = false)
    public String pathImage;
}

