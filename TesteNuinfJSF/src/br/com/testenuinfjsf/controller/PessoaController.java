package br.com.testenuinfjsf.controller;

import java.util.List;

import br.com.testenuinfjsf.dao.DAOFactory;
import br.com.testenuinfjsf.dao.domain.PessoaDAO;
import br.com.testenuinfjsf.model.Pessoa;

public class PessoaController {

	public List<Pessoa> pesquisar(Pessoa pessoa) {
		
		PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
		List<Pessoa> listaPessoas = pessoaDAO.pesquisar(pessoa);
		
		return listaPessoas;	
	}
	
	public List<Pessoa> listar() {
		
		PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
		Pessoa pessoaConsulta = new Pessoa();
		List<Pessoa> listaPessoas = pessoaDAO.pesquisar(pessoaConsulta);
		
		return listaPessoas;	
	}
	public Pessoa salvar(Pessoa pessoa) {
		
		try {
			
			PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
			
			if (pessoa.getId() > 0) {
				
				pessoaDAO.editar(pessoa);
			} else {
				
				pessoaDAO.inserir(pessoa);
			}
			
		} catch (Exception e) {
			
			return null;
		}
		
		return pessoa;
	}
	
	public boolean remover(Pessoa pessoa) {
		
		try {
			
			PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
			pessoaDAO.remover(pessoa);
			
		} catch (Exception e) {
			
			return false;
		}
		
		return true;
	}
	
}