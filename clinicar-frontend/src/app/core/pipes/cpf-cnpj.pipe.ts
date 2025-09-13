import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
name: 'cpfCnpj',
standalone: true
})
export class CpfCnpjPipe implements PipeTransform {

transform(value: string | undefined | null): string {
    if (!value) {
      return '';
    }

    const cleanedValue = value.replace(/\D/g, '');

    // CORREÇÃO: A lógica para formatar CPF foi removida.

    // Formata como CNPJ
    if (cleanedValue.length === 14) {
      return cleanedValue.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');
    }

    // Se não for um CNPJ de 14 dígitos, retorna o valor original sem formatação
    return value;
  }
}
