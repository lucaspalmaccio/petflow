import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Cliente } from '../../models/cliente';
import { ClienteService } from '../../services/cliente.service';
import { CpfPipe } from '../../core/pipes/cpf.pipe';
import { TelefonePipe } from '../../core/pipes/telefone.pipe';

@Component({
selector: 'app-cliente-lista',
standalone: true,
imports: [CommonModule, RouterLink, CpfPipe, TelefonePipe],
templateUrl: './cliente-lista.component.html',
styleUrls: ['./cliente-lista.component.css']
})
export class ClienteListaComponent implements OnInit {
clientes: Cliente[] = [];
mensagem: string | null = null;

constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.carregarClientes();
  }

  carregarClientes(): void {
    this.clienteService.getClientes().subscribe({
      next: (dados) => { this.clientes = dados; },
      error: (err) => { this.mensagem = 'Não foi possível carregar a lista de clientes.'; }
    });
  }

  excluirCliente(id?: number): void {
    if (id && confirm('Tem certeza que deseja excluir este cliente?')) {
      this.clienteService.excluirCliente(id).subscribe({
        next: () => {
          this.mensagem = 'Cliente excluído com sucesso!';
          this.carregarClientes();
        },
        error: (err) => { this.mensagem = 'Erro ao excluir o cliente.'; }
      });
    }
  }
}
