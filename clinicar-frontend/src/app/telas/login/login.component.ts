import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink]
})
export class LoginComponent {
  credenciais = { email: '', senha: '' };
  error: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    // Envia apenas os campos esperados pelo backend
    this.authService.login({
      email: this.credenciais.email,
      senha: this.credenciais.senha
    }).subscribe({
      next: (res) => {
        this.error = '';
        // Redireciona para a página inicial ou dashboard após login
        this.router.navigate(['/dashboard']);
      },
      error: (err: any) => {
        console.error(err);
        this.error = 'E-mail ou senha inválidos!';
      }
    });
  }
}
