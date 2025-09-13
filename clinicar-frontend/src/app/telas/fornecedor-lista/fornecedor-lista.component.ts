import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Fornecedor } from '../../models/fornecedor';
import { FornecedorService } from '../../services/fornecedor.service';

// 1. Importar os Pipes para formatação
import { CpfCnpjPipe } from '../../core/pipes/cpf-cnpj.pipe';
import { TelefonePipe } from '../../core/pipes/telefone.pipe';

@Component({
selector: 'app-fornecedor-lista',
standalone: true,
// 2. Adicionar os Pipes aos imports do componente
imports: [CommonModule, RouterLink, CpfCnpjPipe, TelefonePipe],
templateUrl: './fornecedor-lista.component.html',
styleUrls: ['./fornecedor-lista.component.css']
})
export class FornecedorListaComponent implements OnInit {
fornecedores: Fornecedor[] = [];
mensagem: string | null = null;

constructor(private fornecedorService: FornecedorService) { }

  ngOnInit(): void {
    this.carregarFornecedores();
  }

  carregarFornecedores(): void {
    this.fornecedorService.getFornecedores().subscribe({
      next: (dados) => this.fornecedores = dados,
      error: (err) => {
        console.error('Erro ao carregar fornecedores', err);
        this.mensagem = 'Não foi possível carregar a lista de fornecedores.';
      }
    });
  }

  excluirFornecedor(id?: number): void {
    if (id && confirm('Tem certeza que deseja excluir este fornecedor?')) {
      this.fornecedorService.excluirFornecedor(id).subscribe({
        next: () => {
          this.mensagem = 'Fornecedor excluído com sucesso!';
          this.carregarFornecedores();
          setTimeout(() => this.mensagem = null, 3000);
        },
        error: (err) => {
          console.error('Erro ao excluir fornecedor', err);
          this.mensagem = 'Erro ao excluir o fornecedor.';
        }
      });
    }
  }
}

