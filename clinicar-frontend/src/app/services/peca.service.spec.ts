import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Peca } from '../models/peca';

@Injectable({
  providedIn: 'root'
})
export class PecaService {
  private apiUrl = 'http://localhost:8080/api/pecas'; // URL do CRUD de Peças

  constructor(private http: HttpClient) { }

  getPecas(): Observable<Peca[]> {
    return this.http.get<Peca[]>(this.apiUrl);
  }

  getPeca(id: number): Observable<Peca> {
    return this.http.get<Peca>(`${this.apiUrl}/${id}`);
  }

  criarPeca(peca: Peca): Observable<Peca> {
    return this.http.post<Peca>(this.apiUrl, peca);
  }

  atualizarPeca(id: number, peca: Peca): Observable<Peca> {
    return this.http.put<Peca>(`${this.apiUrl}/${id}`, peca);
  }

  excluirPeca(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
