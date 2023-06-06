package br.com.fiap.donatedine.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "T_USUARIOS")
public class Usuario implements UserDetails {
    
    @Id
    @JsonIgnore
    @Column(name = "PK_ID", columnDefinition = "CHAR(36)")
    public String id;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z\s]{3,}$", message = "Nome deve conter no mínimo 3 caracteres e nenhum pode ser numérico")
    @Column(nullable = false, name = "NOME")
    private String nome;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email só pode conter caracteres alfanuméricos e os especiais: _ . - @")
    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;

    @NotNull
    @NotBlank 
    @Size(min = 8, max = 60, message = "Senha deve ter tamanho entre 8 e 60 caracteres")
    @Column(nullable = false, name = "SENHA")
    private String senha;

    @Column(columnDefinition = "TIMESTAMP", nullable = false, name = "DATA_CRIACAO")
    public LocalDateTime dataCriacao = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }
    
    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario(String nome, String email, String senha) {
        if(!setNome(nome))
            throw new IllegalArgumentException("Nome inválido");

        if(!setEmail(email))
            throw new IllegalArgumentException("Email inválido");

        if(!setSenha(senha))
            throw new IllegalArgumentException("Senha inválida");
    }

    public boolean setNome(String novoNome) {
        if(validarNome(novoNome))
        {
            nome = novoNome;
            return true;
        }

        return false;
    }

    public boolean setEmail(String novoEmail) {
        if(validarEmail(novoEmail))
        {
            email = novoEmail;
            return true;
        }

        return false;
    }

    public boolean setSenha(String novaSenha) {
        if(validarSenha(novaSenha))
        {
            senha = novaSenha;
            return true;
        }

        return false;
    }

    private boolean validarNome(String nome)
    {
        String regex = "^(?=.*[a-zA-Z])[a-zA-Z\s]{3,}$";
        java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(regex);

        return padrao.matcher(nome).matches();
    }

    private boolean validarSenha(String senha)
    {
        if(senha.length() >= 8){
            return true;
        }
            
        return false;
    }

    private boolean validarEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(regex);

        return padrao.matcher(email).matches();
    }
}
