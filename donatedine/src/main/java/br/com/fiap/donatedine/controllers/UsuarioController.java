package br.com.fiap.donatedine.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.donatedine.crosscutting.dtos.LoginDTO;
import br.com.fiap.donatedine.crosscutting.dtos.TokenDTO;
import br.com.fiap.donatedine.crosscutting.dtos.UsuarioResponseDTO;
import br.com.fiap.donatedine.crosscutting.dtos.UsuarioUpdateDTO;
import br.com.fiap.donatedine.exceptions.RestBadRequestException;
import br.com.fiap.donatedine.models.Usuario;
import br.com.fiap.donatedine.services.TokenService;
import br.com.fiap.donatedine.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public UsuarioController(UsuarioService usuarioService, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("cadastrar")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> cadastrar(@Valid @RequestBody Usuario usuario)
    {
        var usuarioCadastrado = usuarioService.cadastrar(usuario);

        var responseDTO = new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getNome(), usuarioCadastrado.getEmail());

        var entityModel = EntityModel.of(
                responseDTO,
                linkTo(methodOn(UsuarioController.class).cadastrar(usuario)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).atualizar("", new UsuarioUpdateDTO(usuarioCadastrado.getNome(), usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha()), usuarioCadastrado.getId())).withRel("atualizar"),
                linkTo(methodOn(UsuarioController.class).logar(new LoginDTO(usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha()))).withRel("logar")
        );

        return ResponseEntity.created(linkTo(methodOn(UsuarioController.class).cadastrar(usuario)).toUri())
                .body(entityModel);
    }

    @PutMapping("{id}")
    public EntityModel<UsuarioResponseDTO> atualizar(
        @RequestHeader (name="Authorization") String token, 
        @Valid @RequestBody UsuarioUpdateDTO usuario, 
        @PathVariable String id
    ){
        String userIdFromToken = tokenService.getUserIdFromToken(token);
		if(!id.equals(userIdFromToken)) {
			throw new RestBadRequestException("Não autorizado para atualizar o usuário");
		}

        log.info("Atualizando cadastro de usuario pelo id: " + id);

        UsuarioResponseDTO responseService = usuarioService.atualizar(usuario, id);

        if(responseService == null)
            throw new RestBadRequestException("Atualização não efetuada. Tente novamente com dados diferentes.");

        return EntityModel.of(
            responseService,
            linkTo(methodOn(UsuarioController.class).atualizar("", usuario, id)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).cadastrar(new Usuario(usuario.nome(), usuario.email(), usuario.senha()))).withRel("cadastrar"),
            linkTo(methodOn(UsuarioController.class).logar(new LoginDTO(usuario.email(), usuario.senha()))).withRel("logar")
        );
    }

    @PostMapping("login")
    public EntityModel<TokenDTO> logar(@Valid @RequestBody LoginDTO credenciais)
    {
        log.info("solicitando validação das credenciais informadas");

        TokenDTO responseService = usuarioService.logar(credenciais);

        var usuarioId = usuarioService.getUsuarioIDByEmail(credenciais.email());

        return EntityModel.of(
                responseService,
                linkTo(methodOn(UsuarioController.class).logar(credenciais)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).atualizar("", new UsuarioUpdateDTO("novo_nome", "novo_email", "nova_senha"), usuarioId)).withRel("atualizar")
        );
    }
}

