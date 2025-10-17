package br.com.fatec.ipiranga.petflow.repository;

import br.com.fatec.ipiranga.petflow.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Método para verificar se já existe um produto com o mesmo nome.
    Optional<Produto> findByNome(String nome);
}