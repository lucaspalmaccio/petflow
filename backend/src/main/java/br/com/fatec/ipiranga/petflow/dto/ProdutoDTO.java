package br.com.fatec.ipiranga.petflow.dto;

import java.math.BigDecimal;

// DTO genérico para criar, atualizar e retornar dados de um Produto.
public record ProdutoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal precoCusto,
        BigDecimal precoVenda,
        Integer qtdEstoque
) {}