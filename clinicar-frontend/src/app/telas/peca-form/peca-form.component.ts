import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Peca } from '../../models/peca';
import { PecaService } from '../../services/peca.service';

@Component({
  selector: 'app-peca-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './peca-form.component.html',
  styleUrls: ['./peca-form.component.css']
})
export class PecaFormComponent implements OnInit {
  // CORREÇÃO: O objeto agora corresponde exatamente à interface Peca
  peca: Peca = {
    nome: '',
    fabricante: '',
    descricao: '',
    precoVenda: 0,
    quantidadeEstoque: 0
  };
  isEditMode = false;

  constructor(
    private pecaService: PecaService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEditMode = true;
      const id = +idParam;
      this.pecaService.getPeca(id).subscribe((data: Peca) => {
        this.peca = data;
      });
    }
  }

  salvar(): void {
    if (this.isEditMode && this.peca.id) {
      this.pecaService.atualizarPeca(this.peca.id, this.peca).subscribe(() => {
        this.router.navigate(['/pecas']);
      });
    } else {
      this.pecaService.criarPeca(this.peca).subscribe(() => {
        this.router.navigate(['/pecas']);
      });
    }
  }
}

