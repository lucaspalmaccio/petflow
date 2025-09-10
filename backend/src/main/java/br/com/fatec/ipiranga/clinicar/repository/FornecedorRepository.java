package br.com.fatec.ipiranga.clinicar.repository;

import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    List<Fornecedor> findByUsuario(Usuario usuario);
}
