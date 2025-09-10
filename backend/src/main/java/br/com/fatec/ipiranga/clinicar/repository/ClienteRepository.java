package br.com.fatec.ipiranga.clinicar.repository;

import br.com.fatec.ipiranga.clinicar.model.Cliente;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca uma lista de clientes que pertencem a um usuário específico.
     * O Spring Data JPA cria a implementação deste método automaticamente
     * baseado no nome do método.
     *
     * @param usuario O usuário dono dos clientes.
     * @return Uma lista de clientes associados ao usuário.
     */
    List<Cliente> findByUsuario(Usuario usuario);
}

