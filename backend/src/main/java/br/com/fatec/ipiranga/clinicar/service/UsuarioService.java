package br.com.fatec.ipiranga.clinicar.service;

import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(Usuario novoUsuario) {
        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Este e-mail já está em uso.");
        }
        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        novoUsuario.setRole("ROLE_USUARIO");
        return usuarioRepository.save(novoUsuario);
    }
}
