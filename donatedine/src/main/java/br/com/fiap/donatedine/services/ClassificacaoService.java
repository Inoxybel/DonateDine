package br.com.fiap.donatedine.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.donatedine.infra.repositories.ClassificacaoRepository;
import br.com.fiap.donatedine.models.Classificacao;

@Service
public class ClassificacaoService {

    private ClassificacaoRepository classificacaoRepository;

    @Autowired
    public ClassificacaoService(ClassificacaoRepository classificacaoRepository) {
        this.classificacaoRepository = classificacaoRepository;
    }

    public Classificacao criarClassificacao(String classificacaoRealizada) {
        
        var classificacao = new Classificacao();

        classificacao.id = UUID.randomUUID().toString();
        classificacao.classificacao = classificacaoRealizada;
        
        var classificacaoCriada = classificacaoRepository.save(classificacao);

        return classificacaoCriada;
    }

    public Classificacao salvar(Classificacao classificacao) {
        return classificacaoRepository.save(classificacao);
    }
    
}
