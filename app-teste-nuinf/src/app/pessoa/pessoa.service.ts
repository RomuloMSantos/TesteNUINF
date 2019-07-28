import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa } from './pessoa';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';

const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };

@Injectable({providedIn: 'root'})
export class PessoaService {

    constructor(
        private http: HttpClient
    ) {}

    private pathUrl = 'http://localhost:8080/TesteNUINF/pessoa';

    getPessoas(): Observable<Pessoa[]> {

        const url = this.pathUrl+'/listar';

        return this.http.get<Pessoa[]>(url);
    }

    pesquisar(pessoa: Pessoa): Observable<Pessoa[]> {
        const url = this.pathUrl+'/pesquisar/';

        const options = { params: new HttpParams().set('id', pessoa.id ? ''+pessoa.id : '')
                .set('nome', pessoa.nome ? pessoa.nome : '')
                .set('cpf', pessoa.cpf ? pessoa.cpf : '') };

        return this.http.get<Pessoa[]>(url, options);
    }

    salvar(pessoa: Pessoa): Observable<Pessoa> {
    
        var url = this.pathUrl+'/cadastrar';

        if (pessoa.id) {

            url = this.pathUrl+'/editar';
            return this.http.put<Pessoa>(url, pessoa, httpOptions);

        }
   
        return this.http.post<Pessoa>(url, pessoa, httpOptions);

    }

    deletar(id: number): Observable<Pessoa> {
    
        const url = this.pathUrl+'/remover/'+id;
        return this.http.delete<Pessoa>(url, httpOptions);

    }

}
