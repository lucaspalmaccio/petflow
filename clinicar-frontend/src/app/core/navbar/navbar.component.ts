import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service'; // ✅ caminho correto

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <nav *ngIf="authService.isLoggedIn()">
      <button (click)="goToDashboard()">Voltar ao Dashboard</button>
      <button (click)="logout()">Sair</button>
    </nav>
  `,
  styles: [
    `nav { margin: 10px; }
     button { padding: 5px 10px; margin-right: 5px; cursor: pointer; }`
  ]
})
export class NavbarComponent {
  constructor(public authService: AuthService, private router: Router) {}

  goToDashboard() {
    this.router.navigate(['/dashboard']);
  }

  logout() {
    this.authService.logout();
  }
}
