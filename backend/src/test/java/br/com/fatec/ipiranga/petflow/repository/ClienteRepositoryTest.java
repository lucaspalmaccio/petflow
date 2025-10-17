package br.com.fatec.ipiranga.petflow.repository;

import br.com.fatec.ipiranga.petflow.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void deveSalvarUmNovoCliente() {
        // Cenário: Cria um novo objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setNome("João da Silva");
        cliente.setCpf("123.456.789-00");
        cliente.setEmail("joao.silva@teste.com");

        // Ação: Salva o cliente no banco de dados de teste
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Verificação: Garante que o cliente foi salvo corretamente
        assertThat(clienteSalvo).isNotNull();
        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(clienteSalvo.getNome()).isEqualTo("João da Silva");
    }

    @Test
    public void deveEncontrarClientePorId() {
        // Cenário
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Oliveira");
        cliente.setCpf("987.654.321-11");
        cliente.setEmail("maria.oliveira@teste.com");
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Ação
        Cliente clienteEncontrado = clienteRepository.findById(clienteSalvo.getId()).orElse(null);

        // Verificação
        assertThat(clienteEncontrado).isNotNull();
        assertThat(clienteEncontrado.getId()).isEqualTo(clienteSalvo.getId());
    }

    @Test
    public void deveDeletarUmCliente() {
        // Cenário
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos Souza");
        cliente.setCpf("111.222.333-44");
        cliente.setEmail("carlos.souza@teste.com");
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Ação
        clienteRepository.deleteById(clienteSalvo.getId());

        // Verificação
        boolean clienteExiste = clienteRepository.findById(clienteSalvo.getId()).isPresent();
        assertThat(clienteExiste).isFalse();
    }
}

