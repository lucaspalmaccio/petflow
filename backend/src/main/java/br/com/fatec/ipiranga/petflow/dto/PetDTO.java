package br.com.fatec.ipiranga.petflow.dto;

// DTO para representar os dados de um Pet, tanto na entrada quanto na saída.
public record PetDTO(
        Long id,
        String nome,
        String especie,
        String raca,
        Long clienteId // Apenas o ID do cliente é necessário na maioria dos casos
) {}