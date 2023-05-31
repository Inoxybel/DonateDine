package br.com.fiap.donatedine.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_GS3_Usuario")
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

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    public LocalDateTime dataCriacao = LocalDateTime.now();
}
