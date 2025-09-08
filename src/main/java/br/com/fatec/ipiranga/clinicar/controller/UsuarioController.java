package br.com.fatec.ipiranga.clinicar.controller;

import br.com.fatec.ipiranga.clinicar.model.Usuario;
// CORREÇÃO: Importando a classe do pacote correto (service)
import br.com.fatec.ipiranga.clinicar.service.UsuarioServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    // CORREÇÃO: Usando o nome simples da classe, pois o import correto já foi feito
    private final UsuarioServico usuarioServico;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario novoUsuario) {
        try {
            Usuario usuarioSalvo = usuarioServico.cadastrarUsuario(novoUsuario);
            return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}