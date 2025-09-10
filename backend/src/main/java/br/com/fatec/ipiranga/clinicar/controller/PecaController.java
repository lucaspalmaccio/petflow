package br.com.fatec.ipiranga.clinicar.controller;

import br.com.fatec.ipiranga.clinicar.model.Peca;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.service.PecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pecas")
@RequiredArgsConstructor
public class PecaController {

    private final PecaService pecaService;

    /**
     * Cria uma nova peça associada ao fornecedor do usuário logado.
     */
    @PostMapping
    public Peca criarPeca(@RequestBody Peca peca, @AuthenticationPrincipal Usuario usuario) {
        // Pega o fornecedor do usuário logado
        Fornecedor fornecedor = usuario.getFornecedores().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado para o usuário"));

        return pecaService.salvar(peca, fornecedor);
    }

    /**
     * Lista todas as peças do fornecedor do usuário logado.
     */
    @GetMapping
    public List<Peca> listarPecas(@AuthenticationPrincipal Usuario usuario) {
        Fornecedor fornecedor = usuario.getFornecedores().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado para o usuário"));

        return pecaService.listarPorFornecedor(fornecedor);
    }

    /**
     * Busca uma peça específica pelo id, garantindo que pertence ao fornecedor do usuário.
     */
    @GetMapping("/{id}")
    public Peca buscarPeca(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        Fornecedor fornecedor = usuario.getFornecedores().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado para o usuário"));

        return pecaService.findByIdAndFornecedor(id, fornecedor)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada ou não pertence ao fornecedor"));
    }

    /**
     * Atualiza uma peça existente.
     */
    @PutMapping("/{id}")
    public Peca atualizarPeca(@PathVariable Long id, @RequestBody Peca pecaAtualizada,
                              @AuthenticationPrincipal Usuario usuario) {
        Fornecedor fornecedor = usuario.getFornecedores().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado para o usuário"));

        Peca pecaExistente = pecaService.findByIdAndFornecedor(id, fornecedor)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada ou não pertence ao fornecedor"));

        // Atualiza os campos
        pecaExistente.setNome(pecaAtualizada.getNome());
        pecaExistente.setFabricante(pecaAtualizada.getFabricante());
        pecaExistente.setPrecoCusto(pecaAtualizada.getPrecoCusto());
        pecaExistente.setPrecoVenda(pecaAtualizada.getPrecoVenda());
        pecaExistente.setQuantidadeEstoque(pecaAtualizada.getQuantidadeEstoque());

        return pecaService.salvar(pecaExistente, fornecedor);
    }

    /**
     * Deleta uma peça existente.
     */
    @DeleteMapping("/{id}")
    public void deletarPeca(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        Fornecedor fornecedor = usuario.getFornecedores().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado para o usuário"));

        pecaService.deletar(id, fornecedor);
    }
}
