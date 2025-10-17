package br.com.fatec.ipiranga.petflow.dto;

// DTO para receber as credenciais de login
public record AuthRequestDTO(
        String email,
        String senha
) {}