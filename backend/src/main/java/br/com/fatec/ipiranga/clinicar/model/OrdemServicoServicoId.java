package br.com.fatec.ipiranga.clinicar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

// Classe para a chave primária composta da tabela ordem_servico_servicos
@Data
@Embeddable
public class OrdemServicoServicoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ordem_servico_id")
    private Long ordemServicoId;

    @Column(name = "servico_id")
    private Long servicoId;

    // Construtores, equals e hashCode são importantes para chaves compostas
    public OrdemServicoServicoId() {
    }

    public OrdemServicoServicoId(Long ordemServicoId, Long servicoId) {
        this.ordemServicoId = ordemServicoId;
        this.servicoId = servicoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServicoServicoId that = (OrdemServicoServicoId) o;
        return Objects.equals(ordemServicoId, that.ordemServicoId) &&
                Objects.equals(servicoId, that.servicoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordemServicoId, servicoId);
    }
}

