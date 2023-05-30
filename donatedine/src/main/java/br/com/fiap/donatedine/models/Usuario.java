package br.com.fiap.donatedine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Usuario {
    
    @Id
    @JsonIgnore
    public String email;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    public String senha;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    public String nome;
}
