package br.com.fiap.donatedine.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;
import br.com.fiap.donatedine.crosscutting.dtos.CreateLoteRequestDTO;
import br.com.fiap.donatedine.crosscutting.dtos.LoteResponseDTO;
import br.com.fiap.donatedine.infra.repositories.LoteRepository;
import br.com.fiap.donatedine.models.Lote;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LoteService {

    private Logger log = LoggerFactory.getLogger(LoteService.class);
    private LoteRepository loteRepository;

    @Autowired
    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public LoteResponseDTO criarLote(CreateLoteRequestDTO request) 
    {
        log.info("Criando lote do fornecedor: " + request.fornecedor());

        var lote = new Lote();
        lote.id = UUID.randomUUID().toString();
        lote.quantidade = request.quantidade();
        lote.unidadeMedida = request.unidadeMedida();
        lote.fornecedor = request.fornecedor();
        lote.descricao = request.descricao();

        var loteSalvo = loteRepository.save(lote);

        return new LoteResponseDTO(
            loteSalvo.id, 
            loteSalvo.quantidade, 
            loteSalvo.unidadeMedida, 
            loteSalvo.descricao, 
            loteSalvo.fornecedor, 
            loteSalvo.dataCriacao
        );
    }

    public Lote recuperarLote(String id) 
    {
        log.info("Recuperando lote de id: " + id);

        Optional<Lote> optionalLote = loteRepository.findById(id);
        
        if(optionalLote.isPresent()){
            return optionalLote.get();
        } else {
            throw new EntityNotFoundException("Lote com id " + id + " não encontrado");
        }
    }

    public List<LoteResponseDTO> recuperarLotesPorQuanidade(int quantidade)
    {
        var lotes = loteRepository.findByQuantidade(quantidade);

        List<LoteResponseDTO> loteDtos = lotes.stream()
            .map(lote -> new LoteResponseDTO(
                lote.getId(),
                lote.getQuantidade(),
                lote.getUnidadeMedida(),
                lote.getDescricao(),
                lote.getFornecedor(),
                lote.getDataCriacao()
            ))
            .collect(Collectors.toList());

        return loteDtos;
    }

    public List<LoteResponseDTO> recuperarLotesPorUnidadeDeMedida(UnidadeMedida unidadeMedida)
    {
        var lotes = loteRepository.findByUnidadeMedida(unidadeMedida);

        List<LoteResponseDTO> loteDtos = lotes.stream()
            .map(lote -> new LoteResponseDTO(
                lote.getId(),
                lote.getQuantidade(),
                lote.getUnidadeMedida(),
                lote.getDescricao(),
                lote.getFornecedor(),
                lote.getDataCriacao()
            ))
            .collect(Collectors.toList());

        return loteDtos;
    }

    public List<LoteResponseDTO> recuperarLotesPorFornecedor(String fornecedor)
    {
        var lotes = loteRepository.findByFornecedor(fornecedor);

        List<LoteResponseDTO> loteDtos = lotes.stream()
            .map(lote -> new LoteResponseDTO(
                lote.getId(),
                lote.getQuantidade(),
                lote.getUnidadeMedida(),
                lote.getDescricao(),
                lote.getFornecedor(),
                lote.getDataCriacao()
            ))
            .collect(Collectors.toList());

        return loteDtos;
    }

    public List<LoteResponseDTO> recuperarLotesPorDataCriacao(LocalDateTime dataCriacao)
    {
        var lotes = loteRepository.findByDataCriacao(dataCriacao);

        List<LoteResponseDTO> loteDtos = lotes.stream()
            .map(lote -> new LoteResponseDTO(
                lote.getId(),
                lote.getQuantidade(),
                lote.getUnidadeMedida(),
                lote.getDescricao(),
                lote.getFornecedor(),
                lote.getDataCriacao()
            ))
            .collect(Collectors.toList());

        return loteDtos;
    }
    
    public long count() {
        return loteRepository.count();
    }
    
}
