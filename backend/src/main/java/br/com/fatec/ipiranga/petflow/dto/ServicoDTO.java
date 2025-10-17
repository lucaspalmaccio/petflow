package br.com.fatec.ipiranga.petflow.dto;

import java.math.BigDecimal;

// DTO genérico para criar, atualizar e retornar dados de um Serviço.
public record ServicoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco
) {}