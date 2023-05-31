package br.com.fiap.donatedine.infra.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.donatedine.models.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, String> {

    @Query("SELECT e FROM Estoque e WHERE e.dataCriacao = ?1")
    List<Estoque> findByDataCriacao(LocalDateTime dataCriacao);

}
