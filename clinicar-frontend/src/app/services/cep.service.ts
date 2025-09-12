import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
providedIn: 'root'
})
export class CepService {

constructor(private http: HttpClient) { }

  /**
   * Busca os dados de um endereço a partir de um CEP.
   * @param cep O CEP a ser consultado (apenas números).
   * @returns Um Observable com os dados do endereço.
   */
  buscar(cep: string): Observable<any> {
    // Faz uma chamada GET para a API pública ViaCEP
    return this.http.get(`https://viacep.com.br/ws/${cep}/json/`);
  }
}
