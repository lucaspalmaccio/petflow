import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink]
})
export class CadastroComponent {
  usuario = { nome: '', email: '', senha: '' };
  error: string = '';
  success: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  cadastrar(): void {
    // Certifique-se de enviar os campos corretos
    this.authService.cadastrar({
      nome: this.usuario.nome,
      email: this.usuario.email,
      senha: this.usuario.senha
    }).subscribe({
      next: () => {
        this.success = 'Cadastro realizado com sucesso! Redirecionando para o login...';
        this.error = '';
        setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: (err: any) => {
        console.error(err);
        this.error = 'Erro ao cadastrar. O e-mail já pode estar em uso.';
        this.success = '';
      }
    });
  }
}
