package br.com.fiap.donatedine.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_DOACOES")
public class Doacao {
    
    @Id
    @JsonIgnore
    @Column(name = "PK_ID", columnDefinition = "CHAR(36)")
    public String id;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "FK_ESTOQUE_ID")
    public Estoque estoque;

    @Column(nullable = true, name = "DESCRICAO")
    public String descricao;

    @NotNull
    @Column(nullable = false, name = "CNPJ_DESTINATARIO")
    public String cnpjDestinatario;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP", nullable = false, name = "DATA_CRIACAO")
    public LocalDateTime dataCriacao = LocalDateTime.now();
}

