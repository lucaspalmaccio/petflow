package br.com.fatec.ipiranga.clinicar.service;

import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarPorUsuario(Usuario usuario) {
        return fornecedorRepository.findByUsuario(usuario);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    // NOVO: Método para buscar um único fornecedor por ID. Essencial para a tela de edição.
    public Optional<Fornecedor> findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    @Transactional
    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado, Usuario usuario) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));

        // Validação de segurança: Garante que o utilizador só pode editar os seus próprios fornecedores.
        if (!fornecedor.getUsuario().getId().equals(usuario.getId())) {
            throw new SecurityException("Acesso negado: Você não tem permissão para editar este fornecedor.");
        }

        fornecedor.setNomeFantasia(fornecedorAtualizado.getNomeFantasia());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());

        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public void excluir(Long id, Usuario usuario) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));

        // Validação de segurança: Garante que o utilizador só pode excluir os seus próprios fornecedores.
        if (!fornecedor.getUsuario().getId().equals(usuario.getId())) {
            throw new SecurityException("Acesso negado: Você não tem permissão para excluir este fornecedor.");
        }

        fornecedorRepository.deleteById(id);
    }
}
