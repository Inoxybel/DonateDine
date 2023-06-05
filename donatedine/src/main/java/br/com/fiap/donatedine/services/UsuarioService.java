package br.com.fiap.donatedine.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.donatedine.crosscutting.dtos.LoginDTO;
import br.com.fiap.donatedine.crosscutting.dtos.TokenDTO;
import br.com.fiap.donatedine.crosscutting.dtos.UsuarioResponseDTO;
import br.com.fiap.donatedine.crosscutting.dtos.UsuarioUpdateDTO;
import br.com.fiap.donatedine.exceptions.RestNotFoundException;
import br.com.fiap.donatedine.infra.repositories.UsuarioRepository;
import br.com.fiap.donatedine.models.Usuario;

@Service
public class UsuarioService {
	Logger log = LoggerFactory.getLogger(UsuarioService.class);

	private UsuarioRepository usuarioRepository;
	private AuthenticationManager manager;
	private PasswordEncoder encoder;
	private TokenService tokenService;

	@Autowired
	public UsuarioService(
		UsuarioRepository usuarioRepository, 
		AuthenticationManager manager, 
		PasswordEncoder encoder, 
		TokenService tokenService
	) {
		this.usuarioRepository = usuarioRepository;
		this.manager = manager;
		this.encoder = encoder;
		this.tokenService = tokenService;
	}

	public Usuario cadastrar(Usuario usuario)
    {
        log.info("Cadastrando usuario.");
        usuario.setId(UUID.randomUUID().toString());
		usuario.setSenha(encoder.encode(usuario.getSenha()));
        
		var usuarioSalvo = usuarioRepository.save(usuario);

		return usuarioSalvo;
    }

	public UsuarioResponseDTO atualizar(UsuarioUpdateDTO usuario, String id) 
	{
		log.info("Atualizando cadastro de usuario pelo id: " + id);
		Usuario repositoryResponse = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		boolean isUpdatable = false;

		if(usuario.nome() != null && !usuario.nome().equals(repositoryResponse.getNome())) {
			isUpdatable = repositoryResponse.setNome(usuario.nome());
		}

		if(usuario.email() != null && !usuario.email().equals(repositoryResponse.getEmail())) {
			isUpdatable = repositoryResponse.setEmail(usuario.email());
		}

		if(usuario.senha() != null && !usuario.senha().equals(repositoryResponse.getSenha())) {
			isUpdatable = repositoryResponse.setSenha(encoder.encode(usuario.senha()));
		}

		if(isUpdatable) {
			var respostaAtualizacao = atualizarUsuario(repositoryResponse);

			return new UsuarioResponseDTO(
					respostaAtualizacao.getId(),
					respostaAtualizacao.getNome(),
					respostaAtualizacao.getEmail()
			);
		}

		return null;
	}

	public Usuario recuperar(String id) {
		log.info("Recuperando cadastro de usuario pelo id: " + id);

		Usuario usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		return usuario;
	}

	public TokenDTO logar(LoginDTO credenciais) {
		manager.authenticate(credenciais.toAuthentication());

		var usuarioId = usuarioRepository.findByEmail(credenciais.email()).get().id;

		return tokenService.generateToken(credenciais, usuarioId	);
	}

	public String getUsuarioIDByEmail(String email)
	{
		var usuario = usuarioRepository.findByEmail(email).get();
		
		return usuario.id;
	}

	private Usuario atualizarUsuario(Usuario usuario)
    {
        log.info("Atualizando usuario: " + usuario);

		return usuarioRepository.saveAndFlush(usuario);
    }
}
