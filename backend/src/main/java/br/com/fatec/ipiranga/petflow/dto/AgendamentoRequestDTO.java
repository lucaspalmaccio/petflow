package br.com.fatec.ipiranga.petflow.dto;

import java.time.LocalDateTime;
import java.util.Set;

// DTO para receber uma solicitação de criação de agendamento.
// Usa apenas os IDs das entidades relacionadas para simplificar.
public record AgendamentoRequestDTO(
        LocalDateTime dataHora,
        Long clienteId,
        Long petId,
        Set<Long> servicosIds // Um conjunto de IDs dos serviços a serem agendados
) {}