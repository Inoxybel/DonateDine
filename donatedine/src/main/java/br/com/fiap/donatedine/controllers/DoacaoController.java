package br.com.fiap.donatedine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.donatedine.crosscutting.dtos.CreateDoacaoRequestDTO;
import br.com.fiap.donatedine.crosscutting.dtos.DoacaoResponseDTO;
import br.com.fiap.donatedine.services.DoacaoService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/doacao")
public class DoacaoController {

    private DoacaoService doacaoService;

    @Autowired
    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }
    
    @PostMapping("/estoque/{idEstoque}")
    public ResponseEntity<EntityModel<DoacaoResponseDTO>> criarDoacao(@PathVariable String idEstoque, @Valid @RequestBody CreateDoacaoRequestDTO request) {
        var doacao = doacaoService.criarDoacao(idEstoque, request);

        if (doacao == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        EntityModel<DoacaoResponseDTO> doacaoResource = EntityModel.of(doacao);
        WebMvcLinkBuilder estoqueLink = linkTo(methodOn(EstoqueController.class).recuperarEstoque(idEstoque));
        doacaoResource.add(estoqueLink.withRel("estoque"));

        return ResponseEntity.ok(doacaoResource);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<DoacaoResponseDTO>> recuperarDoacao(@PathVariable String id) {
        var doacao = doacaoService.recuperarDoacao(id);
        
        if (doacao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        EntityModel<DoacaoResponseDTO> doacaoResource = EntityModel.of(doacao);
        WebMvcLinkBuilder estoqueLink = linkTo(methodOn(EstoqueController.class).recuperarEstoque(doacao.estoqueId()));
        doacaoResource.add(estoqueLink.withRel("estoque"));

        return ResponseEntity.ok(doacaoResource);
    }
    
    @GetMapping("cnpj/{cnpj}")
    public ResponseEntity<List<EntityModel<DoacaoResponseDTO>>> recuperarDoacoesPorCnpj(@PathVariable String cnpj) {
        List<DoacaoResponseDTO> doacoes = doacaoService.recuperarDoacoesPorCnpj(cnpj);
        
        if (doacoes == null || doacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        List<EntityModel<DoacaoResponseDTO>> doacoesResources = doacoes.stream()
                .map(doacao -> {
                    EntityModel<DoacaoResponseDTO> doacaoResource = EntityModel.of(doacao);
                    WebMvcLinkBuilder estoqueLink = linkTo(methodOn(EstoqueController.class).recuperarEstoque(doacao.estoqueId()));
                    doacaoResource.add(estoqueLink.withRel("estoque"));
                    return doacaoResource;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(doacoesResources);
    }
}
