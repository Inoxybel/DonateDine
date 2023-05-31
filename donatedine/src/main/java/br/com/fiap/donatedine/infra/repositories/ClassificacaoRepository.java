package br.com.fiap.donatedine.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.donatedine.models.Classificacao;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Classificacao, String> {

    @Query("SELECT c FROM Classificacao c WHERE c.classificacao = ?1")
    List<Classificacao> findByClassificacao(String classificacao);

}
