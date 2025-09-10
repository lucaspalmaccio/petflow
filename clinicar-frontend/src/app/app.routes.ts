import { Routes } from '@angular/router';
import { LoginComponent } from './telas/login/login.component';
import { CadastroComponent } from './telas/cadastro/cadastro.component';
import { DashboardComponent } from './telas/dashboard/dashboard.component';
import { PecaListaComponent } from './telas/peca-lista/peca-lista.component';
import { PecaFormComponent } from './telas/peca-form/peca-form.component';
import { ClienteListaComponent } from './telas/cliente-lista/cliente-lista.component';
import { ClienteFormComponent } from './telas/cliente-form/cliente-form.component';
import { AuthGuard } from './core/auth.guard';
import { FornecedorListaComponent } from './telas/fornecedor-lista/fornecedor-lista.component';
import { FornecedorFormComponent } from './telas/fornecedor-form/fornecedor-form.component';

export const routes: Routes = [
  // Rotas públicas
  { path: 'login', component: LoginComponent },
  { path: 'cadastro', component: CadastroComponent },

  // Rotas protegidas (só podem ser acessadas após o login)
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },

  { path: 'pecas', component: PecaListaComponent, canActivate: [AuthGuard] },
  { path: 'pecas/novo', component: PecaFormComponent, canActivate: [AuthGuard] },
  { path: 'pecas/editar/:id', component: PecaFormComponent, canActivate: [AuthGuard] },

  { path: 'clientes', component: ClienteListaComponent, canActivate: [AuthGuard] },
  { path: 'clientes/novo', component: ClienteFormComponent, canActivate: [AuthGuard] },
  { path: 'clientes/editar/:id', component: ClienteFormComponent, canActivate: [AuthGuard] },

  { path: 'fornecedores', component: FornecedorListaComponent, canActivate: [AuthGuard] },
  { path: 'fornecedores/novo', component: FornecedorFormComponent, canActivate: [AuthGuard] },
  { path: 'fornecedores/editar/:id', component: FornecedorFormComponent, canActivate: [AuthGuard] },

  // Rota padrão: redireciona para o dashboard se logado, ou para login se não.
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },

  // Rota coringa para páginas não encontradas
  { path: '**', redirectTo: '/login' }
];
