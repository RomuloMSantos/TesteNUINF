import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListComponent } from './pessoa/list/list.component';
import { FormComponent } from './pessoa/form/form.component';

const routes: Routes = [
    {path: 'lista-pessoa', component: ListComponent},
    {path: 'cadastra-pessoa', component: FormComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
