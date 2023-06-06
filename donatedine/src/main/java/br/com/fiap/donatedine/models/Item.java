package br.com.fiap.donatedine.models;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_ITENS")
public class Item {
    
    @Id
    @JsonIgnore
    @Column(name = "PK_ID", columnDefinition = "CHAR(36)")
    public String id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "FK_ESTOQUE_ID")
    public Estoque estoque;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_CLASSIFICACAO_ID")
    public Classificacao classificacao;    

    @NotNull
    @NotBlank
    @Column(nullable = false, name = "NOME")
    public String nome;

    @NotNull
    @Column(nullable = false, name = "IMAGEM_PATH")
    public String imagemPath;
}

