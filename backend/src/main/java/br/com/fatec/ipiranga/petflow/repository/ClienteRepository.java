package br.com.fatec.ipiranga.petflow.repository;

import br.com.fatec.ipiranga.petflow.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // ADICIONE ESTA LINHA SE ELA NÃO EXISTIR
    // Ela cria o método que o seu service está tentando chamar.
    Optional<Cliente> findByCpf(String cpf);
}