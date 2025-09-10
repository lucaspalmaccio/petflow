package br.com.fatec.ipiranga.clinicar.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}
