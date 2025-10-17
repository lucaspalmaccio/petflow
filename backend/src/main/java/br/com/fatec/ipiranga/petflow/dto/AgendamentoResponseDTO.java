package br.com.fatec.ipiranga.petflow.dto;

import br.com.fatec.ipiranga.petflow.enums.StatusAgendamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

// DTO para enviar os detalhes completos de um agendamento para o front-end.
// Retorna os objetos DTO completos das entidades relacionadas para exibição.
public record AgendamentoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        StatusAgendamento status,
        BigDecimal valorTotal,
        ClienteResponseDTO cliente, // DTO aninhado com os dados do cliente
        PetDTO pet,                 // DTO aninhado com os dados do pet
        Set<ServicoDTO> servicos    // Conjunto de DTOs dos serviços
) {}