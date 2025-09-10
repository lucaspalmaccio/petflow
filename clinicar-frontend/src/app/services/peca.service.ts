import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Peca } from '../models/peca'; // Você precisará criar este modelo

@Injectable({
  providedIn: 'root'
})
export class PecaService {
private apiUrl = '/api/pecas';
  constructor(private http: HttpClient) { }

  /**
   * Busca a lista de todas as peças do usuário logado.
   */
  getPecas(): Observable<Peca[]> {
    return this.http.get<Peca[]>(this.apiUrl);
  }

  /**
   * Busca uma peça específica pelo seu ID.
   * @param id O ID da peça a ser buscada.
   */
  getPeca(id: number): Observable<Peca> {
    return this.http.get<Peca>(`${this.apiUrl}/${id}`);
  }

  /**
   * Cria uma nova peça no banco de dados.
   * @param peca O objeto Peca com os dados a serem salvos.
   */
  criarPeca(peca: Peca): Observable<Peca> {
    return this.http.post<Peca>(this.apiUrl, peca);
  }

  /**
   * Atualiza uma peça existente.
   * @param id O ID da peça a ser atualizada.
   * @param peca O objeto Peca com os novos dados.
   */
  atualizarPeca(id: number, peca: Peca): Observable<Peca> {
    return this.http.put<Peca>(`${this.apiUrl}/${id}`, peca);
  }

  /**
   * Deleta uma peça pelo seu ID.
   * @param id O ID da peça a ser deletada.
   */
  deletarPeca(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

