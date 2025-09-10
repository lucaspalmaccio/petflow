package br.com.fatec.ipiranga.clinicar.controller;

import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios") // Define o caminho base para todos os endpoints deste controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Define um endpoint para o cadastro de usuários
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario novoUsuario) {
        try {
            Usuario usuarioSalvo = usuarioService.cadastrarUsuario(novoUsuario);
            // Retorna o usuário salvo com o status HTTP 201 Created
            return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Se o e-mail já existir, retorna um erro HTTP 400 Bad Request
            // (Em um projeto maior, usaríamos um @ControllerAdvice para tratar exceções)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}