package br.com.fatec.ipiranga.clinicar.repository;

import br.com.fatec.ipiranga.clinicar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo e-mail.
     *
     * @param email e-mail do usuário
     * @return Optional contendo o usuário, ou vazio se não encontrado
     */
    Optional<Usuario> findByEmail(String email);
}
