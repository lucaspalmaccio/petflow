import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Fornecedor } from '../models/fornecedor.model';

@Injectable({
  providedIn: 'root'
})
export class FornecedorService {
private apiUrl = '/api/fornecedores';
  constructor(private http: HttpClient, private authService: AuthService) { }

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({ 'Authorization': `Bearer ${token}` });
  }

  getFornecedores(): Observable<Fornecedor[]> {
    return this.http.get<Fornecedor[]>(`${this.apiUrl}/meus`, { headers: this.getHeaders() });
  }

  criarFornecedor(fornecedor: Fornecedor): Observable<Fornecedor> {
    return this.http.post<Fornecedor>(this.apiUrl, fornecedor, { headers: this.getHeaders() });
  }

  atualizarFornecedor(fornecedor: Fornecedor): Observable<Fornecedor> {
    return this.http.put<Fornecedor>(`${this.apiUrl}/${fornecedor.id}`, fornecedor, { headers: this.getHeaders() });
  }

  excluirFornecedor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }
}
