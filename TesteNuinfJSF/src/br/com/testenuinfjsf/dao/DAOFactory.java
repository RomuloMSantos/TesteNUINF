package br.com.testenuinfjsf.dao;

import br.com.testenuinfjsf.dao.domain.PessoaDAO;
import br.com.testenuinfjsf.dao.impl.PessoaDAOImpl;

public class DAOFactory {

	public static PessoaDAO getPessoaDAO() {
		return new PessoaDAOImpl();
	}
	
}
