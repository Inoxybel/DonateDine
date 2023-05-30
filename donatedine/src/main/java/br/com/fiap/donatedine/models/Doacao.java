package br.com.fiap.donatedine.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Doacao {
    
    @Id
    @JsonIgnore
    public String id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_estoque")
    public String idEstoque;

    @Column(nullable = false)
    public String descricao;

    @NotNull
    @Column(nullable = false)
    public String cnpjDestinatario;

    @NotNull
    @Column(nullable = false)
    public LocalDate dataCriacao;
}
