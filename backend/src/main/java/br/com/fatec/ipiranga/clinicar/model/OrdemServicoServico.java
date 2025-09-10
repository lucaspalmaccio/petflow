package br.com.fatec.ipiranga.clinicar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

// Entidade para a tabela associativa ordem_servico_servicos
@Data
@Entity
@Table(name = "ordem_servico_servicos")
public class OrdemServicoServico {

    @EmbeddedId // Chave primária composta
    private OrdemServicoServicoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ordemServicoId")
    @JoinColumn(name = "ordem_servico_id")
    private OrdemServico ordemServico;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("servicoId")
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @Column(name = "preco_cobrado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCobrado;
}
