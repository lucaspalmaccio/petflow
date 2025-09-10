import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PecaForm } from './peca-form';

describe('PecaForm', () => {
  let component: PecaForm;
  let fixture: ComponentFixture<PecaForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PecaForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PecaForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
