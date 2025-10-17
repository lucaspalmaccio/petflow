package br.com.fatec.ipiranga.petflow.service;

import br.com.fatec.ipiranga.petflow.dto.ServicoDTO;
import br.com.fatec.ipiranga.petflow.model.Servico;
import br.com.fatec.ipiranga.petflow.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional(readOnly = true)
    public List<ServicoDTO> findAll() {
        return servicoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ServicoDTO create(ServicoDTO servicoDTO) {
        servicoRepository.findByNome(servicoDTO.nome()).ifPresent(s -> {
            throw new RuntimeException("Serviço com este nome já existe.");
        });
        Servico servico = toEntity(servicoDTO);
        return toDTO(servicoRepository.save(servico));
    }

    // TODO: Implementar métodos update, delete, findById

    private ServicoDTO toDTO(Servico servico) {
        return new ServicoDTO(servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco());
    }

    private Servico toEntity(ServicoDTO dto) {
        Servico servico = new Servico();
        servico.setId(dto.id());
        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setPreco(dto.preco());
        return servico;
    }
}