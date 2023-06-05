package br.com.fiap.donatedine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.donatedine.crosscutting.dtos.CreateEstoqueRequestDTO;
import br.com.fiap.donatedine.crosscutting.dtos.EstoqueResponseDTO;
import br.com.fiap.donatedine.services.EstoqueService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    
    private Logger log = LoggerFactory.getLogger(EstoqueController.class);
    private EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<EstoqueResponseDTO>> criarEstoque( @Valid @RequestBody CreateEstoqueRequestDTO request) {
        log.info("Solicitando criação de um novo estoque.");
    
        var estoque = estoqueService.criarEstoque(request.idLote());
        var entityModel = EntityModel.of(
            estoque
        );
    
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<EstoqueResponseDTO>> recuperarEstoque(@PathVariable String id)
    {
        log.info("Solicitando recuperação do estoque com id: ", id);

        var estoque = estoqueService.recuperarEstoque(id);

        var estoqueResponse = new EstoqueResponseDTO(
            estoque.id,
            estoque.lote.id,
            estoque.doacao != null ? estoque.doacao.id : null,
            estoque.dataCriacao
        );


        var entityModel = EntityModel.of(
            estoqueResponse
        );

        return ResponseEntity.ok(entityModel);
    }
}
