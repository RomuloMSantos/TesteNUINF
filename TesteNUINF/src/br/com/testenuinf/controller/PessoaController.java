package br.com.testenuinf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.testenuinf.dao.DAOFactory;
import br.com.testenuinf.dao.domain.PessoaDAO;
import br.com.testenuinf.model.Pessoa;
import br.com.testenuinf.model.Telefone;

@RestController
@RequestMapping("pessoa")
@CrossOrigin (methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PessoaController {

	@GetMapping(value = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pessoa>> pesquisar(@RequestParam String id, @RequestParam String nome, @RequestParam String cpf) {
		
		PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
		
		Pessoa pessoaConsulta = new Pessoa();
		
		if (null != id && !id.isEmpty()) {
			
			pessoaConsulta.setId(Long.parseLong(id));
		}
		
		pessoaConsulta.setCpf(cpf);
		pessoaConsulta.setNome(nome);
		
		List<Pessoa> listaPessoas = pessoaDAO.pesquisar(pessoaConsulta);
		
		return new ResponseEntity<List<Pessoa>>(listaPessoas, HttpStatus.OK);	
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Pessoa>> listar() {
		
		PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
		Pessoa pessoaConsulta = new Pessoa();
		List<Pessoa> listaPessoas = pessoaDAO.pesquisar(pessoaConsulta);
		
		return new ResponseEntity<List<Pessoa>>(listaPessoas, HttpStatus.OK);	
	}
	
	@PostMapping(value= "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrar(@RequestBody Pessoa pessoa) {
		
		try {
			
			PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
			List<Telefone> telefones = pessoa.getTelefones();
			pessoa.setTelefones(null);
			
			for (Telefone telefone : telefones) {
				telefone.setPessoa(pessoa);
			}
			
			pessoa.setTelefones(telefones);
			
			pessoa = pessoaDAO.inserir(pessoa);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value= "/editar", consumes = {
	        MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editar(@RequestBody Pessoa pessoa) {
		
		try {
			
			PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
			
			List<Telefone> telefones = pessoa.getTelefones();
			pessoa.setTelefones(null);
			
			for (Telefone telefone : telefones) {
				telefone.setPessoa(pessoa);
			}
			
			pessoa.setTelefones(telefones);
			
			pessoaDAO.editar(pessoa);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/remover/{id}")
	public ResponseEntity<?> remover(@PathVariable long id) {
		
		try {
			
			PessoaDAO pessoaDAO = DAOFactory.getPessoaDAO();
			Pessoa pessoa = new Pessoa();
			pessoa.setId(id);
			pessoaDAO.remover(pessoa);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}