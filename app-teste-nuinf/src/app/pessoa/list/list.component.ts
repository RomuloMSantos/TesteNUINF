import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pessoa } from '../pessoa';
import { PessoaService} from '../pessoa.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  pessoas : Pessoa[] = [];
  filtro: Pessoa = new Pessoa();

  constructor(
    private router: Router,
    private pessoaService: PessoaService
  ) { }

  ngOnInit() {
      this.listar();
  }

  private listar() {

    this.pessoaService.getPessoas().subscribe(pessoas => this.pessoas = pessoas);
  }

  pesquisar(): void {
    this.pessoas = [];
    this.pessoaService.pesquisar(this.filtro).subscribe(pessoas => this.pessoas = pessoas);
  }

  editar(idPessoa: number) {
    this.router.navigate(['/cadastra-pessoa/', idPessoa]);
  }

  deletar(pessoa: Pessoa) {

    if(confirm('Tem certeza?')) {

        this.pessoaService.deletar(pessoa.id).subscribe(() => this.listar());
    }

  }

  calculaIdade(dataNascimentoStr: string) {

    var dataAtual = new Date();
    var ano = parseInt(dataNascimentoStr.substring(6,10));
    var mes = parseInt(dataNascimentoStr.substring(3,5)) - 1;
    var dia = parseInt(dataNascimentoStr.substring(0,2));
    var dataNascimento = new Date(ano, mes, dia);
    var idadeMilisegundo = dataAtual.getTime() - dataNascimento.getTime();
    var idade = idadeMilisegundo/31557600000;
    return Math.trunc(idade);

  }

}
