package br.com.fatec.ipiranga.clinicar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

// Entidade para a tabela associativa ordem_servico_pecas
@Data
@Entity
@Table(name = "ordem_servico_pecas")
public class OrdemServicoPeca {

    @EmbeddedId // Chave primária composta
    private OrdemServicoPecaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ordemServicoId") // Mapeia o campo ordemServicoId da chave composta
    @JoinColumn(name = "ordem_servico_id")
    private OrdemServico ordemServico;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pecaId") // Mapeia o campo pecaId da chave composta
    @JoinColumn(name = "peca_id")
    private Peca peca;

    @Column(name = "quantidade_usada", nullable = false)
    private int quantidadeUsada;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;
}
