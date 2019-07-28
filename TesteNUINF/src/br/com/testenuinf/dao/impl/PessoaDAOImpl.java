package br.com.testenuinf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.testenuinf.dao.domain.PessoaDAO;
import br.com.testenuinf.model.Pessoa;

public class PessoaDAOImpl implements PessoaDAO {

	@Override
	public Pessoa inserir(Pessoa pessoa) {

		EntityManagerFactory factory = null;
		EntityManager manager = null;

		try {

			factory = Persistence.createEntityManagerFactory("TesteNUINF");

			manager = factory.createEntityManager();

			manager.getTransaction().begin();        
			manager.persist(pessoa);
			manager.getTransaction().commit(); 

		} finally {

			if (null != manager) {

				manager.close();
			}

			if (null != factory) {

				factory.close();
			}

		}

		return pessoa;
	}

	@Override
	public void remover(Pessoa pessoa) {

		EntityManagerFactory factory = null;
		EntityManager manager = null;

		try {

			factory = Persistence.createEntityManagerFactory("TesteNUINF");

			manager = factory.createEntityManager();

			pessoa = manager.find(Pessoa.class, pessoa.getId());
			
			manager.getTransaction().begin();    
			manager.remove(pessoa);
			manager.getTransaction().commit(); 

		} finally {

			if (null != manager) {

				manager.close();
			}

			if (null != factory) {

				factory.close();
			}

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> pesquisar(Pessoa pessoa) {

		List<Pessoa> listaRetorno = new ArrayList<Pessoa>();
		EntityManagerFactory factory = null;
		EntityManager manager = null;

		try {

			factory = Persistence.createEntityManagerFactory("TesteNUINF");

			manager = factory.createEntityManager();

			if (pessoa.getId() > 0) {

				Pessoa retornoConsulta = manager.find(Pessoa.class, pessoa.getId());

				if (null != retornoConsulta) {

					listaRetorno.add(retornoConsulta);
				}

			} else {
				
				String queryStr = "select p from Pessoa as p where 1=1";

				if (null != pessoa.getNome() && !pessoa.getNome().isEmpty()) {

					queryStr += " and lower(p.nome) like '%" + pessoa.getNome().toLowerCase() + "%'";
				}

				if (null != pessoa.getCpf() && !pessoa.getCpf().isEmpty()) {

					queryStr += " and p.cpf like '" + pessoa.getCpf() + "'";
				}


				listaRetorno = manager.createQuery(queryStr).getResultList();

			}

		} finally {

			if (null != manager) {

				manager.close();
			}

			if (null != factory) {

				factory.close();
			}

		}

		return listaRetorno;
	}

	@Override
	public void editar(Pessoa pessoa) {


		EntityManagerFactory factory = null;
		EntityManager manager = null;

		try {

			factory = Persistence.createEntityManagerFactory("TesteNUINF");

			manager = factory.createEntityManager();

			manager.getTransaction().begin();        
			manager.merge(pessoa);
			manager.getTransaction().commit(); 

		} finally {

			if (null != manager) {

				manager.close();
			}

			if (null != factory) {

				factory.close();
			}

		}

	}

}
