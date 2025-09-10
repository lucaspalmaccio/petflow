package br.com.fatec.ipiranga.clinicar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String telefone;

    @Column(unique = true)
    private String email;

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos = new ArrayList<>();

    // NOVO: Relação com Usuario. Vários clientes podem pertencer a um usuário.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false) // Garante que todo cliente tenha um usuário
    @JsonIgnore
    private Usuario usuario;
}

