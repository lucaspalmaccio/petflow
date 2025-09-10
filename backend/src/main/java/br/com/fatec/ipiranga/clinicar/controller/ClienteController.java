package br.com.fatec.ipiranga.clinicar.controller;

import br.com.fatec.ipiranga.clinicar.model.Cliente;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    /**
     * Endpoint para cadastrar um novo cliente.
     * O cliente será associado automaticamente ao usuário que está fazendo a requisição.
     *
     * @param cliente O objeto Cliente vindo do corpo da requisição.
     * @param usuario O usuário logado, injetado pelo Spring Security.
     * @return O cliente salvo com o status 201 Created.
     */
    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente, @AuthenticationPrincipal Usuario usuario) {
        Cliente clienteSalvo = clienteService.salvar(cliente, usuario);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    /**
     * Endpoint para listar todos os clientes do usuário logado.
     *
     * @param usuario O usuário logado, injetado pelo Spring Security.
     * @return Uma lista de clientes pertencentes ao usuário.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarPorUsuario(@AuthenticationPrincipal Usuario usuario) {
        List<Cliente> clientes = clienteService.listarPorUsuario(usuario);
        return ResponseEntity.ok(clientes);
    }
}

