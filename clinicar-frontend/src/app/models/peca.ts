export interface Peca {
  id?: number;
  nome: string;
  fabricante: string;
  descricao?: string;
  precoCusto?: number;
  precoVenda: number;
  // O nome correto da propriedade, consistente com o backend
  quantidadeEstoque: number;
}

