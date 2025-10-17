package br.com.fatec.ipiranga.petflow.repository;

import br.com.fatec.ipiranga.petflow.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Busca todos os agendamentos de um cliente específico.
    List<Agendamento> findByClienteId(Long clienteId);

    // Busca todos os agendamentos de um pet específico.
    List<Agendamento> findByPetId(Long petId);

    // Busca agendamentos em um intervalo de datas (útil para a agenda/calendário).
    List<Agendamento> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);

    // Verifica se já existe um agendamento em um horário específico, para evitar conflitos.
    Optional<Agendamento> findByDataHora(LocalDateTime dataHora);
}