package br.com.fatec.ipiranga.clinicar.controller;

import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping("/meus")
    public List<Fornecedor> listarMeusFornecedores(@AuthenticationPrincipal Usuario usuario) {
        return fornecedorService.listarPorUsuario(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        return fornecedorService.findById(id)
                // Validação de segurança: Garante que o utilizador só pode ver os seus próprios fornecedores
                .filter(fornecedor -> fornecedor.getUsuario().getId().equals(usuario.getId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fornecedor criarFornecedor(@AuthenticationPrincipal Usuario usuario,
                                      @RequestBody Fornecedor fornecedor) {
        fornecedor.setUsuario(usuario); // Associa o fornecedor ao utilizador logado
        return fornecedorService.salvar(fornecedor);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizarFornecedor(@PathVariable Long id,
                                          @RequestBody Fornecedor fornecedorAtualizado,
                                          @AuthenticationPrincipal Usuario usuario) {
        // O método de atualização agora recebe o utilizador logado para validação de segurança
        return fornecedorService.atualizar(id, fornecedorAtualizado, usuario);
    }

    @DeleteMapping("/{id}")
    public void excluirFornecedor(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        // O método de exclusão agora recebe o utilizador logado para validação de segurança
        fornecedorService.excluir(id, usuario);
    }
}

