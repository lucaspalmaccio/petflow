import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { CpfCnpjDirective } from '../../core/masks/cpf-cnpj.directive';

@Component({
selector: 'app-cadastro',
templateUrl: './cadastro.component.html',
styleUrls: ['./cadastro.component.css'],
standalone: true,
imports: [
CommonModule,
FormsModule,
RouterLink,
CpfCnpjDirective
]
})
export class CadastroComponent {
// Adicione o campo cpfCnpj ao objeto do utilizador
usuario = { nome: '', cpfCnpj: '', email: '', senha: '' };
error: string = '';
success: string = '';

constructor(private authService: AuthService, private router: Router) { }

  cadastrar(): void {
    // Lembre-se de remover a máscara antes de enviar para o backend!
    const usuarioParaSalvar = {
      ...this.usuario,
      cpfCnpj: this.usuario.cpfCnpj.replace(/\D/g, '') // Envia apenas os números
    };

    this.authService.cadastrar(usuarioParaSalvar).subscribe({
      next: () => {
        this.success = 'Cadastro realizado com sucesso! Será redirecionado para o login.';
        setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: (err: any) => {
        this.error = 'Erro ao cadastrar. Verifique os dados e tente novamente.';
        console.error(err);
      }
    });
  }
}

