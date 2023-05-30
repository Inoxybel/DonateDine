package br.com.fiap.donatedine.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Estoque {
    
    @Id
    @JsonIgnore
    public String id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_lote")
    public String idLote;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "id_item")
    public Item item;

    @NotNull
    @Column(nullable = false)
    public LocalDate dataCriacao;
}
