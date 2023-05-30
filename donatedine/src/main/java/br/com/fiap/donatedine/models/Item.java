package br.com.fiap.donatedine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Item {
    
    @Id
    @JsonIgnore
    //@GeneratedValue(strategy = GenerationType.SEQUENCE) <-- Necessário criar sequencia no banco (arquivo DDL DML)
    //@GeneratedValue(strategy = GenerationType.IDENTITY) <-- Isso é compatível com Oracle?
    public String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classificacao")
    public String idClassificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estoque")
    public String idEstoque;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    public String nome;

    @NotNull
    @Column(nullable = false)
    public String pathImage;
}
