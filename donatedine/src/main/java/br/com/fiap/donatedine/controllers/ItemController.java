package br.com.fiap.donatedine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.donatedine.crosscutting.dtos.CreateItemRequestDTO;
import br.com.fiap.donatedine.crosscutting.dtos.ItemResponseDTO;
import br.com.fiap.donatedine.crosscutting.dtos.PaginationResponseDTO;
import br.com.fiap.donatedine.services.ItemService;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemController {
    
    private Logger log = LoggerFactory.getLogger(ItemController.class);
    private ItemService itemService;

    @Autowired
    public ItemController(
        ItemService itemService
    ) {
        this.itemService = itemService;
    }

    @PostMapping("/estoque/{idEstoque}/item")
    public ResponseEntity<Object> criarItem(
        @PathVariable String idEstoque,
        @Valid @RequestBody List<CreateItemRequestDTO> request
    )
    {
        log.info("Criando item para estoque id: " + idEstoque);

        var items = itemService.criarItens(idEstoque, request);

        var collectionModel = CollectionModel.of(
            items.stream().map(EntityModel::of).collect(Collectors.toList())
        );

        return ResponseEntity.ok(collectionModel);

    }

    @GetMapping("/estoque/{idEstoque}/itens")
    public ResponseEntity<EntityModel<PaginationResponseDTO<ItemResponseDTO>>> recuperarItens(
        @PathVariable String idEstoque, 
        @PageableDefault(size = 5) Pageable paginacao
    )
    {
        log.info("Solicitando busca dos itens relacionados ao estoque de id: ", idEstoque);

        var itensEncontrados = itemService.recuperarTodosItems(idEstoque, paginacao);

        var entityModel = EntityModel.of(
            itensEncontrados
        );

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/estoque/{idEstoque}/item/{idItem}")
    public ResponseEntity<EntityModel<ItemResponseDTO>> recuperarItem(
        @PathVariable String idEstoque,
        @PathVariable String idItem
    )
    {
        log.info("Solicitando busca do item de id: ", idItem);

        var itemEncontrado = itemService.recuperarItem(idEstoque, idItem);

        var response = new ItemResponseDTO(
            itemEncontrado.id, 
            itemEncontrado.estoque.id, 
            itemEncontrado.classificacao.id, 
            itemEncontrado.nome, 
            itemEncontrado.classificacao.classificacao,
            itemEncontrado.imagemPath
        );

        var pageable = PageRequest.of(0, 5);
        var entityModel = EntityModel.of(
            response,
            linkTo(methodOn(ItemController.class).recuperarItem(idEstoque, idItem)).withSelfRel(),
            linkTo(methodOn(ItemController.class).recuperarItens(idEstoque, pageable)).withRel("Relacao")
        );

        return ResponseEntity.ok(entityModel);
    }
}
