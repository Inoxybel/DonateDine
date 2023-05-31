package br.com.fiap.donatedine.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.donatedine.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    @Query("SELECT i FROM Item i WHERE i.nome = ?1")
    List<Item> findByNome(String nome);
    
}
