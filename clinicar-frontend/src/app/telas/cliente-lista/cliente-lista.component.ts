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
  mensagemErro: string = '';

  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.carregarClientes();
  }

  carregarClientes(): void {
    this.clienteService.getClientes().subscribe({
      next: (data) => {
        this.clientes = data;
      },
      error: (err) => {
        console.error('Erro ao carregar clientes', err);
        this.mensagemErro = 'Não foi possível carregar a lista de clientes. Tente novamente mais tarde.';
      }
    });
  }
}

