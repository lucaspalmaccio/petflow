package br.com.fatec.ipiranga.petflow.service;

import br.com.fatec.ipiranga.petflow.dto.ClienteRequestDTO;
import br.com.fatec.ipiranga.petflow.dto.ClienteResponseDTO;
import br.com.fatec.ipiranga.petflow.dto.PetDTO;
import br.com.fatec.ipiranga.petflow.enums.PerfilUsuario;
import br.com.fatec.ipiranga.petflow.model.Cliente;
import br.com.fatec.ipiranga.petflow.model.Pet;
import br.com.fatec.ipiranga.petflow.model.Usuario;
import br.com.fatec.ipiranga.petflow.repository.ClienteRepository;
import br.com.fatec.ipiranga.petflow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // TODO: Injetar o PasswordEncoder do Spring Security para criptografar a senha

    @Transactional
    public ClienteResponseDTO create(ClienteRequestDTO requestDTO) {
        if (usuarioRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        if (clienteRepository.findByCpf(requestDTO.cpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(requestDTO.nome());
        novoUsuario.setEmail(requestDTO.email());
        novoUsuario.setSenha(requestDTO.senha()); // Lembre-se de criptografar!
        novoUsuario.setPerfil(PerfilUsuario.CLIENTE);

        Cliente novoCliente = new Cliente();
        novoCliente.setCpf(requestDTO.cpf());
        novoCliente.setTelefone(requestDTO.telefone());
        novoCliente.setEndereco(requestDTO.endereco());
        novoCliente.setUsuario(novoUsuario);

        if (requestDTO.pets() != null && !requestDTO.pets().isEmpty()) {
            List<Pet> pets = requestDTO.pets().stream()
                    .map(petDTO -> {
                        Pet pet = new Pet();
                        pet.setNome(petDTO.nome());
                        pet.setEspecie(petDTO.especie());
                        pet.setRaca(petDTO.raca());
                        pet.setCliente(novoCliente);
                        return pet;
                    }).collect(Collectors.toList());
            novoCliente.setPets(pets);
        }

        Cliente clienteSalvo = clienteRepository.save(novoCliente);
        return toResponseDTO(clienteSalvo);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return toResponseDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Mude de 'private' para 'public' para que outros serviços possam usá-lo.
    public ClienteResponseDTO toResponseDTO(Cliente cliente) {
        List<PetDTO> petsDTO = cliente.getPets() != null ? cliente.getPets().stream()
                .map(pet -> new PetDTO(pet.getId(), pet.getNome(), pet.getEspecie(), pet.getRaca(), cliente.getId()))
                .collect(Collectors.toList()) : Collections.emptyList();

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                petsDTO
        );
    }
}