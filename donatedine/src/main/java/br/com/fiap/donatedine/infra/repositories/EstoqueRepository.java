package br.com.fiap.donatedine.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.donatedine.models.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, String> {

}
