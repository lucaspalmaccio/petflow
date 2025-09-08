package br.com.fatec.ipiranga.clinicar.service;

import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServico {

    // Injeção de dependências via construtor (melhor prática)
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param novoUsuario O objeto Usuario com os dados do novo usuário.
     * @return O Usuario salvo, com ID e senha criptografada.
     * @throws IllegalArgumentException se o e-mail já estiver em uso.
     */
    public Usuario cadastrarUsuario(Usuario novoUsuario) {
        // 1. Verifica se o email já existe no banco de dados
        if (usuarioRepository.findByEmail(novoUsuario.getEmail()) != null) {
            throw new IllegalArgumentException("Este e-mail já está em uso.");
        }

        // 2. Criptografa a senha antes de salvar
        String senhaCriptografada = bCryptPasswordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        // 3. Definir uma role padrão para novos usuários
        novoUsuario.setRole("ROLE_USUARIO");

        // 4. Salva o novo usuário no banco de dados
        return usuarioRepository.save(novoUsuario);
    }
}