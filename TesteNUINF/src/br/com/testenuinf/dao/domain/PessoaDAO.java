package br.com.testenuinf.dao.domain;

import java.util.List;

import br.com.testenuinf.model.Pessoa;

public interface PessoaDAO {

	public Pessoa inserir(Pessoa pessoa);
	public void remover(Pessoa pessoa);
	public List<Pessoa> pesquisar(Pessoa pessoa);
	public void editar(Pessoa pessoa);
	
}