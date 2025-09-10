package br.com.fatec.ipiranga.clinicar.service;

import br.com.fatec.ipiranga.clinicar.model.Cliente;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    /**
     * Salva um novo cliente no banco de dados, associando-o ao usuário logado.
     *
     * @param cliente O objeto Cliente a ser salvo.
     * @param usuario O usuário autenticado que está realizando a operação.
     * @return O Cliente que foi salvo no banco.
     */
    public Cliente salvar(Cliente cliente, Usuario usuario) {
        // Regra de negócio: associa o cliente ao usuário que está logado
        cliente.setUsuario(usuario);
        return clienteRepository.save(cliente);
    }

    /**
     * Lista todos os clientes que pertencem a um usuário específico.
     *
     * @param usuario O usuário autenticado.
     * @return Uma lista contendo apenas os clientes do usuário logado.
     */
    public List<Cliente> listarPorUsuario(Usuario usuario) {
        return clienteRepository.findByUsuario(usuario);
    }
}

