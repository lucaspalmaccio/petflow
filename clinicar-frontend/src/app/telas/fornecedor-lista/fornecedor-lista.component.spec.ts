import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedorLista } from './fornecedor-lista';

describe('FornecedorLista', () => {
  let component: FornecedorLista;
  let fixture: ComponentFixture<FornecedorLista>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FornecedorLista]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedorLista);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
