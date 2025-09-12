import { CpfCnpj } from './cpf-cnpj';

describe('CpfCnpj', () => {
  it('should create an instance', () => {
    const directive = new CpfCnpj();
    expect(directive).toBeTruthy();
  });
});
