package br.com.fatec.ipiranga.clinicar.controller;

import br.com.fatec.ipiranga.clinicar.model.Fornecedor;
import br.com.fatec.ipiranga.clinicar.model.Usuario;
import br.com.fatec.ipiranga.clinicar.service.AuthenticationService;
import br.com.fatec.ipiranga.clinicar.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final AuthenticationService authenticationService;

    @GetMapping("/meus")
    public List<Fornecedor> listarMeusFornecedores(@AuthenticationPrincipal Usuario usuario) {
        return fornecedorService.listarPorUsuario(usuario);
    }

    @PostMapping
    public Fornecedor criarFornecedor(@AuthenticationPrincipal Usuario usuario,
                                      @RequestBody Fornecedor fornecedor) {
        fornecedor.setUsuario(usuario);
        return fornecedorService.salvar(fornecedor);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizarFornecedor(@PathVariable Long id,
                                          @RequestBody Fornecedor fornecedorAtualizado) {
        return fornecedorService.atualizar(id, fornecedorAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluirFornecedor(@PathVariable Long id) {
        fornecedorService.excluir(id);
    }
}
