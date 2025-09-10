export interface Cliente {
  id?: number; // O '?' indica que é opcional (não existe ao criar)
  nome: string;
  cpf: string;
  telefone?: string; // Opcional
  email?: string;    // Opcional
  cep?: string;
  logradouro?: string;
  bairro?: string;
  cidade?: string;
  uf?: string;
  // A referência ao usuário não é necessária no frontend para as operações de CRUD
}
