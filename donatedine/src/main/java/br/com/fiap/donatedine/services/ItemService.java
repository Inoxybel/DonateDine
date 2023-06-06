package br.com.fiap.donatedine.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.donatedine.crosscutting.dtos.CreateItemRequestDTO;
import br.com.fiap.donatedine.crosscutting.dtos.ItemResponseDTO;
import br.com.fiap.donatedine.crosscutting.dtos.PaginationResponseDTO;
import br.com.fiap.donatedine.infra.repositories.ItemRepository;
import br.com.fiap.donatedine.models.Item;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {
    
    Logger log = LoggerFactory.getLogger(ItemService.class);
    private ItemRepository itemRepository;
    private ClassificacaoService classificacaoService;
    private EstoqueService estoqueService;

    @Autowired
    public ItemService(
        ItemRepository itemRepository,
        ClassificacaoService classificacaoService,
        EstoqueService estoqueService
    ) {
        this.itemRepository = itemRepository;
        this.classificacaoService = classificacaoService;
        this.estoqueService = estoqueService;
    }

    public PaginationResponseDTO<ItemResponseDTO> recuperarTodosItems(String idEstoque, Pageable paginacao)
    {
        log.info("Buscando todos itens no repositório.");
    
        var items = itemRepository.getAllItems(idEstoque, paginacao);

        List<ItemResponseDTO> itemDtos = items.getContent().stream()
            .map(item -> new ItemResponseDTO(
                item.getId(),
                item.getEstoque().getId(),
                item.getClassificacao().getId(),
                item.getNome(),
                item.getClassificacao().getClassificacao(),
                item.getImagemPath()
            ))
            .collect(Collectors.toList());
    
        var response = new PaginationResponseDTO<ItemResponseDTO>(
            itemDtos,
            items.getNumber(),
            items.getTotalElements(),
            items.getTotalPages(),
            items.isFirst(),
            items.isLast()
        );
    
        return response;
    }

    public Item recuperarItem(String idEstoque, String idItem) {
        log.info("Buscando item no repositório");
        
        var estoque = estoqueService.recuperarEstoque(idEstoque);

        if(estoque == null){
            throw new EntityNotFoundException("Estoque com id " + idEstoque + " não encontrado");
        }

        Item item = itemRepository.findById(idItem)
            .orElseThrow(() -> new EntityNotFoundException("Item com id " + idItem + " não encontrado"));
    
        return item;
    }
    

    public List<ItemResponseDTO> criarItens(String idEstoque, List<CreateItemRequestDTO> requests) {
        log.info("Criando novos itens vinculados ao estoque de id: ", idEstoque);
        
        var estoque = estoqueService.recuperarEstoque(idEstoque);
    
        List<ItemResponseDTO> items = new ArrayList<>();
        for (CreateItemRequestDTO request : requests) {
            var classificacao = classificacaoService.criarClassificacao(request.classificacao());
    
            var item = new Item();
            item.imagemPath = request.pathImagem();
            item.id = UUID.randomUUID().toString();
            item.estoque = estoque;
            item.classificacao = classificacao;
            item.nome = request.nome();
    
            item = itemRepository.save(item);
    
            classificacao.setItem(item);
            classificacaoService.salvar(classificacao);
    
            estoque.itens.add(item);
    
            items.add(new ItemResponseDTO(
                item.id, 
                item.estoque.id, 
                item.classificacao.id, 
                item.nome, 
                item.classificacao.classificacao,
                item.imagemPath
            ));
        }
    
        estoqueService.persistirEstoque(estoque);
    
        return items;
    }
    
    
}
