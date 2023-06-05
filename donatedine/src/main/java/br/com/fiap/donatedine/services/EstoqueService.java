package br.com.fiap.donatedine.services;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.donatedine.crosscutting.dtos.EstoqueResponseDTO;
import br.com.fiap.donatedine.infra.repositories.EstoqueRepository;
import br.com.fiap.donatedine.models.Estoque;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EstoqueService {

    Logger log = LoggerFactory.getLogger(ItemService.class);
    private LoteService loteService;
    private EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(LoteService loteService, EstoqueRepository estoqueRepository) {
        this.loteService = loteService;
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque recuperarEstoque(String idEstoque) {
        log.info("Recuperando estoque de id: " + idEstoque);

        Optional<Estoque> optionalEstoque = estoqueRepository.findById(idEstoque);
        
        if(optionalEstoque.isPresent()){
            return optionalEstoque.get();
        } else {
            throw new EntityNotFoundException("Estoque com id " + idEstoque + " não encontrado");
        }
    }
    
    public EstoqueResponseDTO persistirEstoque(Estoque estoque) {
        estoque = estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(
            estoque.id, 
            estoque.lote.id, 
            estoque.doacao != null ? estoque.doacao.id : null,
            estoque.dataCriacao
        );
    }

    public EstoqueResponseDTO criarEstoque(String idLote) {
        var lote = loteService.recuperarLote(idLote);

        if(lote == null)
            return null;

        var estoque = new Estoque();
        estoque.lote = lote;
        estoque.id = UUID.randomUUID().toString();

        estoque = estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(
            estoque.id, 
            estoque.lote.id, 
            null, 
            estoque.dataCriacao
        );
    }

    public long count() {
        return estoqueRepository.count();
    }
    
}
