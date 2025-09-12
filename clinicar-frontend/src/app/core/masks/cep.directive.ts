import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
selector: '[appCepMask]',
standalone: true
})
export class CepDirective {

constructor(private el: ElementRef) { }

  @HostListener('input', ['$event'])
  onInputChange(event: Event): void {
    const initialValue = this.el.nativeElement.value;
    let value = initialValue.replace(/\D/g, '');

    if (value.length > 8) {
      value = value.substring(0, 8);
    }

    // Aplica a máscara de CEP
    if (value.length > 5) {
      value = value.replace(/(\d{5})(\d)/, '$1-$2');
    }

    this.el.nativeElement.value = value;
  }
}
