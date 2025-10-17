package br.com.fatec.ipiranga.petflow.dto;

// DTO para enviar o token JWT de volta ao cliente após o login
public record AuthResponseDTO(
        String token
) {}