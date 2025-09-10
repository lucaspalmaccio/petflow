import { Component, OnInit } from '@angular/core';
import { FornecedorService } from '../../services/fornecedor.service';
import { Fornecedor } from '../../models/fornecedor.model';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-fornecedor-lista',
  templateUrl: './fornecedor-lista.component.html',
  styleUrls: ['./fornecedor-lista.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule]
})
export class FornecedorListaComponent implements OnInit {

  listaFornecedores: Fornecedor[] = [];
  erro: string = '';
  fornecedorForm: FormGroup;
  fornecedorSelecionado?: Fornecedor;

  constructor(
    private fornecedorService: FornecedorService,
    private fb: FormBuilder
  ) {
    this.fornecedorForm = this.fb.group({
      nomeFantasia: ['', Validators.required],
      cnpj: ['', Validators.required],
      telefone: ['']
    });
  }

  ngOnInit(): void {
    this.carregarFornecedores();
  }

  carregarFornecedores(): void {
    this.fornecedorService.getFornecedores().subscribe({
      next: (fornecedores: Fornecedor[]) => this.listaFornecedores = fornecedores,
      error: () => this.erro = 'Erro ao carregar fornecedores'
    });
  }

  salvarFornecedor(): void {
    if (this.fornecedorForm.invalid) return;

    const fornecedor: Fornecedor = this.fornecedorForm.value;

    if (this.fornecedorSelecionado) {
      // Se estiver editando, atualiza o fornecedor
      fornecedor.id = this.fornecedorSelecionado.id;
      this.fornecedorService.atualizarFornecedor(fornecedor).subscribe({
        next: () => {
          this.carregarFornecedores();
          this.fornecedorForm.reset();
          this.fornecedorSelecionado = undefined;
        },
        error: () => this.erro = 'Erro ao atualizar fornecedor'
      });
    } else {
      // Se não estiver editando, cria novo fornecedor
      this.fornecedorService.criarFornecedor(fornecedor).subscribe({
        next: () => {
          this.carregarFornecedores();
          this.fornecedorForm.reset();
        },
        error: () => this.erro = 'Erro ao salvar fornecedor'
      });
    }
  }

  editarFornecedor(fornecedor: Fornecedor): void {
    this.fornecedorSelecionado = fornecedor;
    this.fornecedorForm.patchValue({
      nomeFantasia: fornecedor.nomeFantasia,
      cnpj: fornecedor.cnpj,
      telefone: fornecedor.telefone
    });
  }

  excluirFornecedor(fornecedor: Fornecedor): void {
    if (fornecedor.id) {
      this.fornecedorService.excluirFornecedor(fornecedor.id).subscribe({
        next: () => this.carregarFornecedores(),
        error: () => this.erro = 'Erro ao excluir fornecedor'
      });
    }
  }

  cancelarEdicao(): void {
    this.fornecedorSelecionado = undefined;
    this.fornecedorForm.reset();
  }
}
