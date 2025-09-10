import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api'; // URL base do seu backend

  constructor(private http: HttpClient, private router: Router) { }

  cadastrar(usuario: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/usuarios/cadastro`, usuario);
  }

  // ATENÇÃO: Você precisa criar este endpoint no seu backend!
  login(credenciais: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credenciais).pipe(
      tap((resposta: any) => {
        // Assume que o backend retorna um objeto com uma propriedade 'token'
        localStorage.setItem('authToken', resposta.token);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
