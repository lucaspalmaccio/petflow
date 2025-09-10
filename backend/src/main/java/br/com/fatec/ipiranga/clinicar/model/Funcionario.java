package br.com.fatec.ipiranga.clinicar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String cargo;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha; // Lembre-se de armazenar a senha com hash
}
