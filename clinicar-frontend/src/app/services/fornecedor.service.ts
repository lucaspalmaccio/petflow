import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fornecedor } from '../models/fornecedor';

@Injectable({
providedIn: 'root'
})
export class FornecedorService {
private apiUrl = '/api/fornecedores';

constructor(private http: HttpClient) { }

  /**
   * CORREÇÃO: Este método agora aponta para a API '/api/fornecedores/meus'
   * para buscar a lista de fornecedores do utilizador logado.
   */
  getFornecedores(): Observable<Fornecedor[]> {
    return this.http.get<Fornecedor[]>(`${this.apiUrl}/meus`);
  }

  // Busca um único fornecedor pelo ID.
  getFornecedor(id: number): Observable<Fornecedor> {
    return this.http.get<Fornecedor>(`${this.apiUrl}/${id}`);
  }

  // Cria um novo fornecedor.
  criarFornecedor(fornecedor: Fornecedor): Observable<Fornecedor> {
    return this.http.post<Fornecedor>(this.apiUrl, fornecedor);
  }

  // Atualiza um fornecedor existente.
  atualizarFornecedor(id: number, fornecedor: Fornecedor): Observable<Fornecedor> {
    return this.http.put<Fornecedor>(`${this.apiUrl}/${id}`, fornecedor);
  }

  // Exclui um fornecedor.
  excluirFornecedor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
