package br.com.fatec.ipiranga.petflow.dto;

import java.util.List;

// DTO usado para criar ou atualizar um cliente.
// Recebe os dados do usuário e do cliente juntos para facilitar a criação.
public record ClienteRequestDTO(
        String nome,
        String email,
        String senha, // Senha em texto plano, será criptografada no service
        String cpf,
        String telefone,
        String endereco,
        List<PetDTO> pets // Permite criar o cliente já com seus pets
) {}