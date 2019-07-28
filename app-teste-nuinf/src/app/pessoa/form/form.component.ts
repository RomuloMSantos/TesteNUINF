import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '../../../../node_modules/@angular/router';
import {FormControl} from '@angular/forms';

import { PessoaService } from '../pessoa.service';
import { Pessoa, Telefone } from '../pessoa';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  pessoa: Pessoa = new Pessoa();
  novoTelefone: Telefone = new Telefone();


  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private pessoaService: PessoaService
  ) {
  }

  ngOnInit() {
    
    this.activatedRoute.paramMap.subscribe(params => {
        this.pessoa.id = +params.get("id")
    });

    if (this.pessoa.id) {
      var pessoas;
      this.pessoaService.pesquisar(this.pessoa).subscribe(pessoas => this.pessoa = new Pessoa(pessoas[0]));
      
    }
  }

  adicionarTelefone(telefone: Telefone) {

    this.pessoa.adicionarTelefone(this.novoTelefone);
    this.novoTelefone = new Telefone();

  }

  removerTelefone(telefone: Telefone) {

    this.pessoa.removerTelefone(telefone);

  }

  salvar() {

   this.pessoaService.salvar(this.pessoa).subscribe(() => {
        alert("Dados salvos.");
        this.router.navigate(['lista-pessoa/']);
      });
  }
}
