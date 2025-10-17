package br.com.fatec.ipiranga.petflow.dto;

import java.util.List;

// DTO para retornar os dados de um cliente para o front-end.
// Não expõe a senha e retorna a lista completa de pets.
public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        String endereco,
        List<PetDTO> pets
) {}