import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appCpfCnpjMask]',
  standalone: true
})
export class CpfCnpjDirective {

  constructor(private el: ElementRef) { }

  // Escuta o evento 'input' do campo onde a diretiva for aplicada
  @HostListener('input', ['$event'])
  onInputChange(event: Event): void {
    const initialValue = this.el.nativeElement.value;

    // 1. Remove tudo que não for dígito
    let value = initialValue.replace(/\D/g, '');

    // 2. Limita o tamanho para 14 dígitos (tamanho do CNPJ)
    if (value.length > 14) {
      value = value.substring(0, 14);
    }

    // 3. Aplica a máscara de acordo com o tamanho
    if (value.length <= 11) { // É um CPF
      value = value.replace(/(\d{3})(\d)/, '$1.$2');
      value = value.replace(/(\d{3})(\d)/, '$1.$2');
      value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    } else { // É um CNPJ
      value = value.replace(/(\d{2})(\d)/, '$1.$2');
      value = value.replace(/(\d{3})(\d)/, '$1.$2');
      value = value.replace(/(\d{3})(\d)/, '$1/$2');
      value = value.replace(/(\d{4})(\d{1,2})$/, '$1-$2');
    }

    // 4. Atualiza o valor do campo no HTML
    this.el.nativeElement.value = value;
  }
}
