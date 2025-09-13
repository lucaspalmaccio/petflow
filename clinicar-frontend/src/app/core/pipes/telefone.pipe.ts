import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
name: 'telefone',
standalone: true
})
export class TelefonePipe implements PipeTransform {
transform(value: string | undefined | null): string {
    if (!value) {
      return '';
    }
    const telefone = value.replace(/\D/g, '');
    if (telefone.length === 11) {
      return telefone.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    }
    if (telefone.length === 10) {
      return telefone.replace(/(\d{2})(\d{4})(\d{4})/, '($1) $2-$3');
    }
    return value; // Se não for um telefone válido, retorna o original
  }
}
