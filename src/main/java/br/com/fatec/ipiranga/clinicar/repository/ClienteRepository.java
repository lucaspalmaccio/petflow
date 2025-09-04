package br.com.fatec.ipiranga.clinicar.repository;

import br.com.fatec.ipiranga.clinicar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // O Spring Data JPA criará automaticamente os métodos CRUD (save, findById, findAll, delete, etc.)
    // para a entidade Cliente. Não é necessário escrever nenhuma implementação aqui.
}

