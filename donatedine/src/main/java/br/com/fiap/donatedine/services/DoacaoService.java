package br.com.fiap.donatedine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.donatedine.crosscutting.dtos.CreateDoacaoRequestDTO;
import br.com.fiap.donatedine.crosscutting.dtos.DoacaoResponseDTO;
import br.com.fiap.donatedine.infra.repositories.DoacaoRepository;
import br.com.fiap.donatedine.models.Doacao;
import br.com.fiap.donatedine.models.Estoque;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DoacaoService {
    private final DoacaoRepository doacaoRepository;
    private final EstoqueService estoqueService;
    
    @Autowired
    public DoacaoService(DoacaoRepository doacaoRepository, EstoqueService estoqueService) {
        this.doacaoRepository = doacaoRepository;
        this.estoqueService = estoqueService;
    }
    
    public DoacaoResponseDTO criarDoacao(String idEstoque, CreateDoacaoRequestDTO request) {
        Estoque estoque = estoqueService.recuperarEstoque(idEstoque);
    
        if (estoque == null) {
            throw new IllegalArgumentException("Estoque inválido");
        }
    
        Doacao doacao = new Doacao();
        doacao.setId(UUID.randomUUID().toString());
        doacao.setDescricao(request.descricao());
        doacao.setCnpjDestinatario(request.cnpjDestinatario());
        doacao.setEstoque(estoque);
    
        var doacaoSalva = doacaoRepository.save(doacao);

        return new DoacaoResponseDTO(
            doacaoSalva.id, 
            doacaoSalva.estoque.id, 
            doacaoSalva.descricao,
            doacaoSalva.cnpjDestinatario,
            doacaoSalva.dataCriacao
        );
    }
    
    public DoacaoResponseDTO recuperarDoacao(String id) {
        
        var doacao = doacaoRepository.findById(id).orElse(null);

        return new DoacaoResponseDTO(
            doacao.id, 
            doacao.estoque.id, 
            doacao.descricao,
            doacao.cnpjDestinatario,
            doacao.dataCriacao
        );
    }
    
    public List<DoacaoResponseDTO> recuperarDoacoesPorCnpj(String cnpjDestinatario) {
        var doacoes = doacaoRepository.findByCnpjDestinatario(cnpjDestinatario);

        var doacoesResponse = new ArrayList<DoacaoResponseDTO>();

        for (var doacao : doacoes)
        {
            doacoesResponse.add(new DoacaoResponseDTO(
                doacao.id, 
                doacao.estoque.id, 
                doacao.descricao,
                doacao.cnpjDestinatario,
                doacao.dataCriacao
            ));
        }

        return doacoesResponse;
    }

    public long count() {
        return doacaoRepository.count();
    }
}