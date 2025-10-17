package br.com.fatec.ipiranga.petflow.service;

import br.com.fatec.ipiranga.petflow.dto.AgendamentoRequestDTO;
import br.com.fatec.ipiranga.petflow.dto.AgendamentoResponseDTO;
import br.com.fatec.ipiranga.petflow.dto.ClienteResponseDTO; // <-- CORREÇÃO APLICADA AQUI
import br.com.fatec.ipiranga.petflow.dto.PetDTO;
import br.com.fatec.ipiranga.petflow.dto.ServicoDTO;
import br.com.fatec.ipiranga.petflow.enums.StatusAgendamento;
import br.com.fatec.ipiranga.petflow.model.Agendamento;
import br.com.fatec.ipiranga.petflow.model.Cliente;
import br.com.fatec.ipiranga.petflow.model.Pet;
import br.com.fatec.ipiranga.petflow.model.Servico;
import br.com.fatec.ipiranga.petflow.repository.AgendamentoRepository;
import br.com.fatec.ipiranga.petflow.repository.ClienteRepository;
import br.com.fatec.ipiranga.petflow.repository.PetRepository;
import br.com.fatec.ipiranga.petflow.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ClienteService clienteService;

    @Transactional
    public AgendamentoResponseDTO create(AgendamentoRequestDTO requestDTO) {
        if (requestDTO.dataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar em uma data passada.");
        }
        agendamentoRepository.findByDataHora(requestDTO.dataHora()).ifPresent(a -> {
            throw new RuntimeException("Este horário já está ocupado.");
        });

        Cliente cliente = clienteRepository.findById(requestDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        Pet pet = petRepository.findById(requestDTO.petId())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado."));

        if (!pet.getCliente().getId().equals(cliente.getId())) {
            throw new RuntimeException("Este pet não pertence ao cliente informado.");
        }

        Set<Servico> servicos = new HashSet<>(servicoRepository.findAllById(requestDTO.servicosIds()));
        if (servicos.isEmpty() || servicos.size() != requestDTO.servicosIds().size()) {
            throw new RuntimeException("Um ou mais serviços são inválidos.");
        }

        BigDecimal valorTotal = servicos.stream()
                .map(Servico::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setDataHora(requestDTO.dataHora());
        novoAgendamento.setStatus(StatusAgendamento.AGENDADO);
        novoAgendamento.setCliente(cliente);
        novoAgendamento.setPet(pet);
        novoAgendamento.setServicos(servicos);
        novoAgendamento.setValorTotal(valorTotal);

        Agendamento agendamentoSalvo = agendamentoRepository.save(novoAgendamento);

        return toResponseDTO(agendamentoSalvo);
    }

    private AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        ClienteResponseDTO clienteDTO = clienteService.toResponseDTO(agendamento.getCliente());

        PetDTO petDTO = new PetDTO(agendamento.getPet().getId(), agendamento.getPet().getNome(), agendamento.getPet().getEspecie(), agendamento.getPet().getRaca(), clienteDTO.id());

        Set<ServicoDTO> servicosDTO = agendamento.getServicos().stream()
                .map(servico -> new ServicoDTO(servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco()))
                .collect(Collectors.toSet());

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getStatus(),
                agendamento.getValorTotal(),
                clienteDTO,
                petDTO,
                servicosDTO
        );
    }
}