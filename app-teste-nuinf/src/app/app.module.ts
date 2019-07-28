import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {NgxMaskModule} from 'ngx-mask'

import { RouterModule, Routes } from '@angular/router'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListComponent } from './pessoa/list/list.component';
import { FormComponent } from './pessoa/form/form.component';

const routes: Routes = [
  { path: 'cadastra-pessoa/:id', component: FormComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    NgxMaskModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
