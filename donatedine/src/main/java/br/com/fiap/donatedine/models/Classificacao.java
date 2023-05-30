package br.com.fiap.donatedine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Classificacao {
    
    @Id
    @JsonIgnore
    public String id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_item")
    @JsonIgnore
    public Item item;

    public String classificacao;
}