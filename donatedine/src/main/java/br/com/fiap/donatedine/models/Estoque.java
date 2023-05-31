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
@Table(name = "T_GS3_Estoque")
public class Estoque {
    
    @Id
    @JsonIgnore
    public String id;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "id_lote")
    public Lote idLote;

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Item> items;

    @OneToOne(mappedBy = "estoque")
    @JsonIgnore
    public Doacao doacao;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    public LocalDateTime dataCriacao = LocalDateTime.now();
}
