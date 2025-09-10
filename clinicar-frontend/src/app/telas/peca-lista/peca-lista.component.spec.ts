import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PecaLista } from './peca-lista';

describe('PecaLista', () => {
  let component: PecaLista;
  let fixture: ComponentFixture<PecaLista>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PecaLista]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PecaLista);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
