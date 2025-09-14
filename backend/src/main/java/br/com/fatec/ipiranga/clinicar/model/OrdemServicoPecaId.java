package br.com.fatec.ipiranga.clinicar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class OrdemServicoPecaId implements Serializable {

    @Column(name = "ordem_servico_id")
    private Long ordemServicoId;

    @Column(name = "peca_id")
    private Long pecaId;
}
