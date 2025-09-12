import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
selector: '[appCpfMask]',
standalone: true
})
export class CpfDirective {

constructor(private el: ElementRef) { }

  @HostListener('input', ['$event'])
  onInputChange(event: Event): void {
    const initialValue = this.el.nativeElement.value;
    let value = initialValue.replace(/\D/g, ''); // Remove tudo que não é dígito

    if (value.length > 11) {
      value = value.substring(0, 11); // Limita a 11 dígitos
    }

    // Aplica a máscara de CPF
    if (value.length > 9) {
      value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
    } else if (value.length > 6) {
      value = value.replace(/(\d{3})(\d{3})(\d{1,3})/, '$1.$2.$3');
    } else if (value.length > 3) {
      value = value.replace(/(\d{3})(\d{1,3})/, '$1.$2');
    }

    this.el.nativeElement.value = value;
  }
}
