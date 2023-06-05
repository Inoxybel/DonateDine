package br.com.fiap.donatedine.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.fiap.donatedine.crosscutting.dtos.LoginDTO;
import br.com.fiap.donatedine.crosscutting.dtos.TokenDTO;
import br.com.fiap.donatedine.infra.repositories.UsuarioRepository;
import br.com.fiap.donatedine.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    String secret;

    public TokenDTO generateToken(LoginDTO credencial, String id) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withSubject(credencial.email())
                .withClaim("userId", id)
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .withIssuer("DonateDine")
                .sign(alg);

        return new TokenDTO(token, "JWT", "Bearer");
    }

    public Usuario valideAndGetUserBy(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email =  JWT.require(alg)
                .withIssuer("DonateDine")
                .build()
                .verify(token)
                .getSubject()
                ;

        return usuarioRepository.findByEmail(email).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public String getUserIdFromToken(String token) {
        token = token.substring(7); 
        
        Algorithm alg = Algorithm.HMAC256(secret);
        var userId = JWT.require(alg)
                .withIssuer("DonateDine")
                .build()
                .verify(token)
                .getClaim("userId");

        return userId.asString();
    }

}
