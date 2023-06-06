package br.com.fiap.donatedine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_CLASSIFICACOES")
public class Classificacao {
    
    @Id
    @JsonIgnore
    @Column(name = "PK_ID", columnDefinition = "CHAR(36)")
    public String id;

    @OneToOne(mappedBy = "classificacao", cascade = CascadeType.ALL)
    @JsonIgnore
    public Item item;

    @Column(nullable = false, name = "CLASSIFICACAO")
    public String classificacao;
}
