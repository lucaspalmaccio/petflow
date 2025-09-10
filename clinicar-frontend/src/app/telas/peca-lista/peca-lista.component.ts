import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { Peca } from '../../models/peca';
import { PecaService } from '../../services/peca.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-peca-lista',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './peca-lista.component.html',
  styleUrls: ['./peca-lista.component.css']
})
export class PecaListaComponent implements OnInit {
  pecas: Peca[] = [];

  constructor(
    private pecaService: PecaService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.carregarPecas();
  }

  carregarPecas(): void {
    this.pecaService.getPecas().subscribe((data: Peca[]) => {
      this.pecas = data;
    });
  }

  excluir(id?: number): void {
    if (id && confirm('Tem certeza que deseja excluir esta peça?')) {
      // CORREÇÃO: Usando o nome correto do método do serviço ('deletarPeca')
      this.pecaService.deletarPeca(id).subscribe(() => {
        this.carregarPecas(); // Recarrega a lista
      });
    }
  }

  logout(): void {
    this.authService.logout();
  }
}

