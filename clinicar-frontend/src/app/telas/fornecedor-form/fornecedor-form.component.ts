import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Fornecedor } from '../../models/fornecedor';
import { FornecedorService } from '../../services/fornecedor.service';
import { CpfCnpjDirective } from '../../core/masks/cpf-cnpj.directive';
import { TelefoneDirective } from '../../core/masks/telefone.directive';

@Component({
selector: 'app-fornecedor-form',
standalone: true,
imports: [CommonModule, FormsModule, RouterLink, CpfCnpjDirective, TelefoneDirective],
templateUrl: './fornecedor-form.component.html',
styleUrls: ['./fornecedor-form.component.css']
})
export class FornecedorFormComponent implements OnInit {
fornecedor: Fornecedor = {
nomeFantasia: '',
cnpj: '',
telefone: ''
};
isEditMode = false;
mensagemErro: string | null = null; // Renomeado de 'erro' para 'mensagemErro' para clareza

constructor(
    private fornecedorService: FornecedorService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.fornecedorService.getFornecedor(+id).subscribe(data => this.fornecedor = data);
    }
  }

  // Renomeado de 'salvarFornecedor' para 'salvar' para consistência
  salvar(): void {
    this.mensagemErro = null;
    const fornecedorParaSalvar: Fornecedor = {
      ...this.fornecedor,
      cnpj: this.fornecedor.cnpj.replace(/\D/g, ''),
      telefone: this.fornecedor.telefone.replace(/\D/g, '')
    };

    const onSucesso = () => this.router.navigate(['/fornecedores']);
    const onError = (err: any) => {
      this.mensagemErro = 'Não foi possível salvar o fornecedor.';
    };

    if (this.isEditMode && this.fornecedor.id) {
      this.fornecedorService.atualizarFornecedor(this.fornecedor.id, fornecedorParaSalvar)
        .subscribe({ next: onSucesso, error: onError });
    } else {
      this.fornecedorService.criarFornecedor(fornecedorParaSalvar)
        .subscribe({ next: onSucesso, error: onError });
    }
  }
}

