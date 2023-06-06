package br.com.fiap.donatedine.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_ESTOQUES")
public class Estoque {
    
    @Id
    @JsonIgnore
    @Column(name = "PK_ID", columnDefinition = "CHAR(36)")
    public String id;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "FK_LOTE_ID")
    public Lote lote;

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Item> itens;

    @OneToOne(mappedBy = "estoque")
    @JsonIgnore
    public Doacao doacao;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP", nullable = false, name = "DATA_CRIACAO")
    public LocalDateTime dataCriacao = LocalDateTime.now();
}
