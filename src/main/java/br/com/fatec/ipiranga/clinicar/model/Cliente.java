package br.com.fatec.ipiranga.clinicar.model;

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

    // Um cliente pode ter vários veículos. Se o cliente for removido, seus veículos também serão.
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos = new ArrayList<>();
}
