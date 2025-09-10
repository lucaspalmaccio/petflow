package br.com.fatec.ipiranga.clinicar.service;

import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void excluir(Long id) {
        fornecedorRepository.deleteById(id);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        fornecedor.setNomeFantasia(fornecedorAtualizado.getNomeFantasia());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());

        return fornecedorRepository.save(fornecedor);
    }
}
