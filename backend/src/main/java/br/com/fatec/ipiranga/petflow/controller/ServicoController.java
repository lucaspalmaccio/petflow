package br.com.fatec.ipiranga.petflow.controller;

import br.com.fatec.ipiranga.petflow.dto.ServicoDTO;
import br.com.fatec.ipiranga.petflow.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listarTodosOsServicos() {
        List<ServicoDTO> servicos = servicoService.findAll();
        return ResponseEntity.ok(servicos);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> criarServico(@RequestBody ServicoDTO servicoDTO) {
        ServicoDTO novoServico = servicoService.create(servicoDTO);
        return ResponseEntity.status(201).body(novoServico);
    }
}