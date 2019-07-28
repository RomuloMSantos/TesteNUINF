package br.com.testenuinfjsf.dao.domain;

import java.util.List;

import br.com.testenuinfjsf.model.Pessoa;

public interface PessoaDAO {

	public Pessoa inserir(Pessoa pessoa);
	public void remover(Pessoa pessoa);
	public List<Pessoa> pesquisar(Pessoa pessoa);
	public void editar(Pessoa pessoa);
	
}