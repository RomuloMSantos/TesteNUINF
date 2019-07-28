package br.com.testenuinf.dao;

import br.com.testenuinf.dao.domain.PessoaDAO;
import br.com.testenuinf.dao.impl.PessoaDAOImpl;

public class DAOFactory {

	public static PessoaDAO getPessoaDAO() {
		return new PessoaDAOImpl();
	}
	
}