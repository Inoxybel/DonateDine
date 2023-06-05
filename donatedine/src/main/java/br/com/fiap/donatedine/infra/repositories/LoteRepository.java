package br.com.fiap.donatedine.infra.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.donatedine.crosscutting.Enums.UnidadeMedida;
import br.com.fiap.donatedine.models.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, String> {

    @Query("SELECT l FROM Lote l WHERE l.quantidade = ?1")
    List<Lote> findByQuantidade(int quantidade);
    
    @Query("SELECT l FROM Lote l WHERE l.unidadeMedida = ?1")
    List<Lote> findByUnidadeMedida(UnidadeMedida unidadeMedida);

    @Query("SELECT l FROM Lote l WHERE l.fornecedor = ?1")
    List<Lote> findByFornecedor(String fornecedor);

    @Query("SELECT l FROM Lote l WHERE l.dataCriacao >= ?1")
    List<Lote> findByDataCriacao(LocalDateTime dataCriacao);

}
