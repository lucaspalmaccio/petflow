package br.com.fatec.ipiranga.clinicar.repository;

import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {

    /**
     * Retorna todas as peças de um fornecedor específico.
     *
     * @param fornecedor o fornecedor cujas peças devem ser listadas
     * @return lista de peças do fornecedor
     */
    List<Peca> findByFornecedor(Fornecedor fornecedor);
}
