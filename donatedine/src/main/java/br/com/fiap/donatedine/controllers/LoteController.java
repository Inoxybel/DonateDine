package br.com.fiap.donatedine.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;
import br.com.fiap.donatedine.crosscutting.dtos.CreateLoteRequestDTO;
import br.com.fiap.donatedine.crosscutting.dtos.LoteResponseDTO;
import br.com.fiap.donatedine.services.LoteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lote")
public class LoteController {
    
    private Logger log = LoggerFactory.getLogger(LoteController.class);
    private LoteService loteService;

    @Autowired
    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<LoteResponseDTO>> criarLote(@Valid @RequestBody CreateLoteRequestDTO request)
    {
        log.info("Solicitando criação de um novo lote.");

        var lote = loteService.criarLote(request);

        if (lote == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        var entityModel = EntityModel.of(
            lote,
            linkTo(methodOn(LoteController.class).recuperarLote(lote.id())).withSelfRel(),
            linkTo(methodOn(LoteController.class).recuperarLotesPorQuantidade(lote.quantidade())).withRel("lotesPorQuantidade"),
            linkTo(methodOn(LoteController.class).recuperarLotesPorUnidadeDeMedida(lote.unidadeMedida())).withRel("lotesPorUnidadeMedida"),
            linkTo(methodOn(LoteController.class).recuperarLotesPorFornecedor(lote.fornecedor())).withRel("lotesPorFornecedor"),
            linkTo(methodOn(LoteController.class).recuperarLotesPorDataCriacao(lote.dataCriacao())).withRel("lotesPorDataCriacao")
        );

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<LoteResponseDTO>> recuperarLote(@PathVariable String id)
    {
        log.info("Solicitando recuperação do lote com id: " + id);

        var lote = loteService.recuperarLote(id);

        if (lote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var response = new LoteResponseDTO(
            lote.id, 
            lote.quantidade, 
            lote.unidadeMedida, 
            lote.descricao, 
            lote.fornecedor, 
            lote.dataCriacao
        );

        var entityModel = EntityModel.of(
            response,
            linkTo(methodOn(LoteController.class).recuperarLote(id)).withSelfRel()
        );

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/quantidade/{quantidade}")
    public ResponseEntity<CollectionModel<EntityModel<LoteResponseDTO>>> recuperarLotesPorQuantidade(@PathVariable int quantidade) {
        log.info("Solicitando recuperação de lotes com quantidade: " + quantidade);
    
        var lotes = loteService.recuperarLotesPorQuanidade(quantidade);
    
        if (lotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    
        List<EntityModel<LoteResponseDTO>> lotesEntityModel = lotes.stream()
            .map(lote -> EntityModel.of(lote,
                linkTo(methodOn(LoteController.class).recuperarLote(lote.id())).withRel("item")))  // Aqui estou usando lote.id() para recuperar o id.
            .collect(Collectors.toList());
    
        CollectionModel<EntityModel<LoteResponseDTO>> collectionModel = CollectionModel.of(lotesEntityModel,
            linkTo(methodOn(LoteController.class).recuperarLotesPorQuantidade(quantidade)).withSelfRel());
    
        return ResponseEntity.ok(collectionModel);
    }
    
    
    @GetMapping("/unidademedida/{unidadeMedida}")
    public ResponseEntity<CollectionModel<EntityModel<LoteResponseDTO>>> recuperarLotesPorUnidadeDeMedida(@PathVariable UnidadeMedida unidadeMedida)
    {
        log.info("Solicitando recuperação de lotes com unidade de medida: " + unidadeMedida);
    
        var lotes = loteService.recuperarLotesPorUnidadeDeMedida(unidadeMedida);
    
        if (lotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    
        List<EntityModel<LoteResponseDTO>> lotesEntityModel = lotes.stream()
            .map(lote -> EntityModel.of(lote,
                linkTo(methodOn(LoteController.class).recuperarLote(lote.id())).withRel("item")))  // Aqui estou usando lote.id() para recuperar o id.
            .collect(Collectors.toList());
    
        CollectionModel<EntityModel<LoteResponseDTO>> collectionModel = CollectionModel.of(lotesEntityModel,
            linkTo(methodOn(LoteController.class).recuperarLotesPorUnidadeDeMedida(unidadeMedida)).withSelfRel());
    
        return ResponseEntity.ok(collectionModel);
    }
    
    @GetMapping("/fornecedor/{fornecedor}")
    public ResponseEntity<CollectionModel<EntityModel<LoteResponseDTO>>> recuperarLotesPorFornecedor(@PathVariable String fornecedor)
    {
        log.info("Solicitando recuperação de lotes do fornecedor: " + fornecedor);
    
        var lotes = loteService.recuperarLotesPorFornecedor(fornecedor);
    
        if (lotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    
        List<EntityModel<LoteResponseDTO>> lotesEntityModel = lotes.stream()
            .map(lote -> EntityModel.of(lote,
                linkTo(methodOn(LoteController.class).recuperarLote(lote.id())).withRel("item")))  // Aqui estou usando lote.id() para recuperar o id.
            .collect(Collectors.toList());
    
        CollectionModel<EntityModel<LoteResponseDTO>> collectionModel = CollectionModel.of(lotesEntityModel,
            linkTo(methodOn(LoteController.class).recuperarLotesPorFornecedor(fornecedor)).withSelfRel());
    
        return ResponseEntity.ok(collectionModel);
    }
    
    @GetMapping("/datacriacao/{dataCriacao}")
    public ResponseEntity<CollectionModel<EntityModel<LoteResponseDTO>>> recuperarLotesPorDataCriacao(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataCriacao)
    {
        log.info("Solicitando recuperação de lotes com data de criação: " + dataCriacao);
    
        var lotes = loteService.recuperarLotesPorDataCriacao(dataCriacao);
    
        if (lotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    
        List<EntityModel<LoteResponseDTO>> lotesEntityModel = lotes.stream()
            .map(lote -> EntityModel.of(lote,
                linkTo(methodOn(LoteController.class).recuperarLote(lote.id())).withRel("item")))  // Aqui estou usando lote.id() para recuperar o id.
            .collect(Collectors.toList());
    
        CollectionModel<EntityModel<LoteResponseDTO>> collectionModel = CollectionModel.of(lotesEntityModel,
            linkTo(methodOn(LoteController.class).recuperarLotesPorDataCriacao(dataCriacao)).withSelfRel());
    
        return ResponseEntity.ok(collectionModel);
    }
}

