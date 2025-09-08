package br.com.fatec.ipiranga.clinicar.repository;

import br.com.fatec.ipiranga.clinicar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // O Spring Data JPA cria a implementação deste método automaticamente.
    // O Spring Security vai usá-lo para encontrar o usuário pelo email na hora do login.
    UserDetails findByEmail(String email);

}