import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Cliente } from '../../models/cliente';
import { ClienteService } from '../../services/cliente.service';

@Component({
selector: 'app-cliente-lista',
standalone: true,
imports: [CommonModule, RouterLink],
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
      next: (dados) => {
        this.clientes = dados;
      },
      error: (err) => {
        console.error('Erro ao carregar clientes', err);
        this.mensagem = 'Não foi possível carregar a lista de clientes.';
      }
    });
  }

  excluirCliente(id?: number): void {
    if (id && confirm('Tem certeza que deseja excluir este cliente?')) {
      this.clienteService.excluirCliente(id).subscribe({
        next: () => {
          this.mensagem = 'Cliente excluído com sucesso!';
          this.carregarClientes(); // Recarrega a lista
          setTimeout(() => this.mensagem = null, 3000); // Limpa a mensagem após 3 segundos
        },
        error: (err) => {
          console.error('Erro ao excluir cliente', err);
          this.mensagem = 'Erro ao excluir o cliente.';
        }
      });
    }
  }
}

