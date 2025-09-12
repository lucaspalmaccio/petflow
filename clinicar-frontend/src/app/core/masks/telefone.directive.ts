import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
selector: '[appTelefoneMask]',
standalone: true
})
export class TelefoneDirective {

constructor(private el: ElementRef) { }

  @HostListener('input', ['$event'])
  onInputChange(event: Event): void {
    const initialValue = this.el.nativeElement.value;
    let value = initialValue.replace(/\D/g, '');

    if (value.length > 11) {
      value = value.substring(0, 11);
    }

    // Aplica a máscara de telefone (celular ou fixo)
    if (value.length > 10) {
      value = value.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    } else if (value.length > 6) {
      value = value.replace(/(\d{2})(\d{4})(\d{0,4})/, '($1) $2-$3');
    } else if (value.length > 2) {
      value = value.replace(/(\d{2})(\d{0,5})/, '($1) $2');
    } else {
      value = value.replace(/(\d*)/, '($1');
    }

    this.el.nativeElement.value = value;
  }
}
