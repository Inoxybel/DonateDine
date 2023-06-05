package br.com.fiap.donatedine.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.donatedine.models.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, String> {

    @Query("SELECT d FROM Doacao d WHERE d.cnpjDestinatario = ?1")
    List<Doacao> findByCnpjDestinatario(String cnpjDestinatario);

}
