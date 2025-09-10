import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ClienteService } from '../../services/cliente.service';
import { Cliente } from '../../models/cliente';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink]
})
export class ClienteFormComponent implements OnInit {

  // Objeto que será vinculado ao formulário
  cliente: Cliente = { nome: '', cpf: '' };
  isEditMode = false;
  mensagemErro: string = '';

  constructor(
    private clienteService: ClienteService,
    private router: Router,
    private route: ActivatedRoute // Usado para ler parâmetros da URL (como o ID do cliente)
  ) { }

  ngOnInit(): void {
    // Verifica se existe um 'id' na URL
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      // Se existe ID, estamos no modo de edição
      this.isEditMode = true;
      this.clienteService.getCliente(+id).subscribe({
        next: (data) => this.cliente = data,
        error: (err) => this.mensagemErro = 'Falha ao carregar os dados do cliente.'
      });
    }
    // Se não houver ID, o componente inicia no modo de criação com um objeto 'cliente' vazio.
  }

  salvar(): void {
    if (this.isEditMode && this.cliente.id) {
      // Lógica para ATUALIZAR um cliente existente
      this.clienteService.atualizarCliente(this.cliente.id, this.cliente).subscribe({
        next: () => this.router.navigate(['/clientes']),
        error: (err) => this.mensagemErro = 'Falha ao atualizar o cliente.'
      });
    } else {
      // Lógica para CRIAR um novo cliente
      this.clienteService.criarCliente(this.cliente).subscribe({
        next: () => this.router.navigate(['/clientes']),
        error: (err) => this.mensagemErro = 'Falha ao cadastrar o cliente. Verifique os dados.'
      });
    }
  }
}
