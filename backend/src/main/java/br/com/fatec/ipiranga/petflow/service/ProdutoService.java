package br.com.fatec.ipiranga.petflow.service;

import br.com.fatec.ipiranga.petflow.dto.ProdutoDTO;
import br.com.fatec.ipiranga.petflow.model.Produto;
import br.com.fatec.ipiranga.petflow.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        return produtoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ProdutoDTO create(ProdutoDTO produtoDTO) {
        produtoRepository.findByNome(produtoDTO.nome()).ifPresent(p -> {
            throw new RuntimeException("Produto com este nome já existe.");
        });
        Produto produto = toEntity(produtoDTO);
        return toDTO(produtoRepository.save(produto));
    }

    // TODO: Implementar métodos update, delete, findById

    @Transactional
    public void adicionarEstoque(Long produtoId, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setQtdEstoque(produto.getQtdEstoque() + quantidade);
        produtoRepository.save(produto);
    }

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPrecoCusto(), produto.getPrecoVenda(), produto.getQtdEstoque());
    }

    private Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.id());
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPrecoCusto(dto.precoCusto());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setQtdEstoque(dto.qtdEstoque());
        return produto;
    }
}