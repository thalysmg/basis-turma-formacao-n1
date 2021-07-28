import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { ListaResponsaveisComponent } from './view/lista-responsaveis/lista-responsaveis.component';
import { ListaTarefasComponent } from './view/lista-tarefas/lista-tarefas.component';

const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'responsaveis', component: ListaResponsaveisComponent },
    { path: 'tarefas', component: ListaTarefasComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
