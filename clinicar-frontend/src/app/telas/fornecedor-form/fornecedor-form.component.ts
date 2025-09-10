import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { FornecedorService } from '../../services/fornecedor.service';
import { AuthService } from '../../services/auth.service';
import { Fornecedor } from '../../models/fornecedor.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-fornecedor-form',
  templateUrl: './fornecedor-form.component.html',
  styleUrls: ['./fornecedor-form.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule]
})
export class FornecedorFormComponent implements OnInit {

  fornecedorForm!: FormGroup;
  listaFornecedores: Fornecedor[] = [];
  erro: string = '';

  constructor(
    private fb: FormBuilder,
    private fornecedorService: FornecedorService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.fornecedorForm = this.fb.group({
      nomeFantasia: ['', Validators.required],
      cnpj: ['', Validators.required],
      telefone: ['']
    });

    this.carregarFornecedores();
  }

  carregarFornecedores(): void {
    this.fornecedorService.getFornecedores().subscribe({
      next: (fornecedores: Fornecedor[]) => this.listaFornecedores = fornecedores,
      error: (err: any) => this.erro = 'Erro ao carregar fornecedores'
    });
  }

  salvarFornecedor(): void {
    if (this.fornecedorForm.invalid) return;

    const fornecedor: Fornecedor = this.fornecedorForm.value;

    this.fornecedorService.criarFornecedor(fornecedor).subscribe({
      next: (novoFornecedor: Fornecedor) => {
        this.listaFornecedores.push(novoFornecedor);
        this.fornecedorForm.reset();
      },
      error: (err: any) => this.erro = 'Erro ao salvar fornecedor'
    });
  }
}
