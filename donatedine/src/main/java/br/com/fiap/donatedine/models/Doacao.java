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
@Table(name = "T_GS3_Doacao")
public class Doacao {
    
    @Id
    @JsonIgnore
    public String id;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "id_estoque")
    public Estoque estoque;

    @Column(nullable = false)
    public String descricao;

    @NotNull
    @Column(nullable = false)
    public String cnpjDestinatario;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    public LocalDateTime dataCriacao = LocalDateTime.now();
}

