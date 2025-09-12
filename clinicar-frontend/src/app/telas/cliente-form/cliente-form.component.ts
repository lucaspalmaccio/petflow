import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

// 1. IMPORTAÇÕES CORRIGIDAS
import { CpfDirective } from '../../core/masks/cpf.directive';
import { TelefoneDirective } from '../../core/masks/telefone.directive';
// CORREÇÃO: O import estava a apontar para o ficheiro errado (cep.service.ts). Agora aponta para o ficheiro correto da diretiva.
import { CepDirective } from '../../core/masks/cep.directive';
import { Cliente } from '../../models/cliente';
import { ClienteService } from '../../services/cliente.service';
import { CepService } from '../../services/cep.service';

@Component({
selector: 'app-cliente-form',
templateUrl: './cliente-form.component.html',
styleUrls: ['./cliente-form.component.css'],
standalone: true,
imports: [
CommonModule,
FormsModule,
RouterLink,
// 2. DECLARAÇÃO DAS DIRETIVAS (Isto agora funcionará corretamente)
CpfDirective,
TelefoneDirective,
CepDirective
]
})
export class ClienteFormComponent implements OnInit {
cliente: Cliente = {
nome: '',
cpf: '',
telefone: '',
email: '',
cep: '',
logradouro: '',
bairro: '',
cidade: '',
uf: ''
};
isEditMode = false;
mensagemErro: string | null = null;

constructor(
    private clienteService: ClienteService,
    private router: Router,
    private route: ActivatedRoute,
    private cepService: CepService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.clienteService.getCliente(+id).subscribe(data => this.cliente = data);
    }
  }

  buscarCep(): void {
    const cep = this.cliente.cep?.replace(/\D/g, ''); // Remove a máscara do CEP

    if (cep && cep.length === 8) { // Verifica se o CEP tem 8 dígitos
      this.cepService.buscar(cep).subscribe({
        next: (dados) => {
          if (dados.erro) {
            this.mensagemErro = 'CEP não encontrado.';
            this.limparEndereco();
          } else {
            this.cliente.logradouro = dados.logradouro;
            this.cliente.bairro = dados.bairro;
            this.cliente.cidade = dados.localidade;
            this.cliente.uf = dados.uf;
            this.mensagemErro = null;
          }
        },
        error: (err) => {
          console.error('Erro ao buscar CEP:', err);
          this.mensagemErro = 'Ocorreu um erro ao buscar o CEP.';
          this.limparEndereco();
        }
      });
    }
  }

  private limparEndereco(): void {
    this.cliente.logradouro = '';
    this.cliente.bairro = '';
    this.cliente.cidade = '';
    this.cliente.uf = '';
  }

  salvar(): void {
    this.mensagemErro = null;

    const clienteParaSalvar: Cliente = {
      ...this.cliente,
      cpf: this.cliente.cpf.replace(/\D/g, ''),
      telefone: this.cliente.telefone?.replace(/\D/g, ''),
      cep: this.cliente.cep?.replace(/\D/g, '')
    };

    const onSucesso = () => this.router.navigate(['/clientes']);
    const onError = (err: any) => {
      console.error('Erro ao salvar cliente:', err);
      this.mensagemErro = 'Não foi possível salvar o cliente. Verifique os dados e tente novamente.';
    };

    if (this.isEditMode && this.cliente.id) {
      this.clienteService.atualizarCliente(this.cliente.id, clienteParaSalvar)
        .subscribe({ next: onSucesso, error: onError });
    } else {
      this.clienteService.criarCliente(clienteParaSalvar)
        .subscribe({ next: onSucesso, error: onError });
    }
  }
}

