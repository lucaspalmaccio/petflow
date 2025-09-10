package br.com.fatec.ipiranga.clinicar.service;

import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.model.Peca;
import br.com.fatec.ipiranga.clinicar.repository.PecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PecaService {

    private final PecaRepository pecaRepository;

    /**
     * Salva uma peça vinculada a um fornecedor.
     */
    public Peca salvar(Peca peca, Fornecedor fornecedor) {
        peca.setFornecedor(fornecedor);
        return pecaRepository.save(peca);
    }

    /**
     * Lista todas as peças de um fornecedor específico.
     */
    public List<Peca> listarPorFornecedor(Fornecedor fornecedor) {
        return pecaRepository.findByFornecedor(fornecedor);
    }

    /**
     * Busca uma peça pelo id, garantindo que pertence ao fornecedor.
     */
    public Optional<Peca> findByIdAndFornecedor(Long id, Fornecedor fornecedor) {
        return pecaRepository.findById(id)
                .filter(peca -> peca.getFornecedor().getId().equals(fornecedor.getId()));
    }

    /**
     * Remove uma peça pelo id, garantindo que pertence ao fornecedor.
     */
    public void deletar(Long id, Fornecedor fornecedor) {
        Peca peca = findByIdAndFornecedor(id, fornecedor)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada ou não pertence ao fornecedor"));
        pecaRepository.delete(peca);
    }
}
