export class Pessoa {
    
    id : number;
    nome : string;
    cpf : string;
    email : string;
    dataNascimento : string;
    telefones : Telefone[] = [];

    constructor(data?: any) {
        if (data) {
            this.id = data.id;
            this.nome = data.nome;
            this.cpf = data.cpf;
            this.email = data.email;
            this.dataNascimento = data.dataNascimento;
            this.telefones = data.telefones;
        }   
    }
    
    adicionarTelefone(telefone: Telefone): void {
        this.telefones.unshift(telefone);
    }

    removerTelefone(telefone: Telefone): void {
        this.telefones.splice(this.telefones.indexOf(telefone), 1);
    }

}

export class Telefone {
    
    id : number;
    ddd : string;
    numero : string;
}
