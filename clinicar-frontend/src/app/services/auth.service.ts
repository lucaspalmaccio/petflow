// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
private apiUrl = '/api';
  constructor(private http: HttpClient, private router: Router) {}

  // Cadastro de usuário
  cadastrar(usuario: { nome: string; email: string; senha: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/usuarios/cadastro`, usuario);
  }

  // Login de usuário
  login(credenciais: { email: string; senha: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth/login`, credenciais).pipe(
      tap((resposta: any) => {
        if (resposta && resposta.token) {
          localStorage.setItem('authToken', resposta.token);
        }
      })
    );
  }

  // Logout
  logout(): void {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

  // Retorna o token
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Verifica se o usuário está logado
  isLoggedIn(): boolean {
    return !!localStorage.getItem('authToken');
  }
}
