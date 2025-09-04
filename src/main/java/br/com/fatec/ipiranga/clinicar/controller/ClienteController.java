package br.com.fatec.ipiranga.clinicar.controller;

import br.com.fatec.ipiranga.clinicar.model.Cliente;
import br.com.fatec.ipiranga.clinicar.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Endpoint para criar um novo cliente (POST /api/clientes)
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteRepository.save(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    // Endpoint para buscar todos os clientes (GET /api/clientes)
    @GetMapping
    public List<Cliente> listarTodosOsClientes() {
        return clienteRepository.findAll();
    }

    // Endpoint para buscar um cliente por ID (GET /api/clientes/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um cliente (PUT /api/clientes/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalhes) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteDetalhes.getNome());
                    cliente.setCpf(clienteDetalhes.getCpf());
                    cliente.setTelefone(clienteDetalhes.getTelefone());
                    cliente.setEmail(clienteDetalhes.getEmail());
                    cliente.setCep(clienteDetalhes.getCep());
                    cliente.setLogradouro(clienteDetalhes.getLogradouro());
                    cliente.setBairro(clienteDetalhes.getBairro());
                    cliente.setCidade(clienteDetalhes.getCidade());
                    cliente.setUf(clienteDetalhes.getUf());
                    Cliente clienteAtualizado = clienteRepository.save(cliente);
                    return ResponseEntity.ok(clienteAtualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para deletar um cliente (DELETE /api/clientes/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

