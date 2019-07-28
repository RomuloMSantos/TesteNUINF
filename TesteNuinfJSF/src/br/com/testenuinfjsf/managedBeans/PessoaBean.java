package br.com.testenuinfjsf.managedBeans;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.testenuinfjsf.controller.PessoaController;
import br.com.testenuinfjsf.model.Pessoa;
import br.com.testenuinfjsf.model.Telefone;

@ManagedBean
@SessionScoped
public class PessoaBean {

	private long idPessoaEditar;
	private Pessoa pessoaCadastro;
	private Telefone novoTelefone;
	private Pessoa pessoaSelecionada;
	private Telefone telefonePessoaSelecionado;
	private List<Pessoa> listaPessoa;
	
	public PessoaBean () {
		
		this.pessoaCadastro = new Pessoa();
		this.novoTelefone = new Telefone();
			
	}
	
	public Pessoa getPessoaCadastro() {
		return pessoaCadastro;
	}

	public Long getIdPessoaEditar() {
		return idPessoaEditar;
	}

	public void setIdPessoaEditar(Long idPessoaEditar) {
		this.idPessoaEditar = idPessoaEditar;
	}

	public void setPessoaCadastro(Pessoa pessoaCadastro) {
		this.pessoaCadastro = pessoaCadastro;
	}

	public Telefone getNovoTelefone() {
		return novoTelefone;
	}

	public void setNovoTelefone(Telefone novoTelefone) {
		this.novoTelefone = novoTelefone;
	}

	public List<Pessoa> getListaPessoa() {
		
		if (null == this.listaPessoa) {
			
			PessoaController pessoaController = new PessoaController();
			this.listaPessoa = pessoaController.listar();
		}
		return this.listaPessoa;
	}
	
	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	

	public Telefone getTelefonePessoaSelecionado() {
		return telefonePessoaSelecionado;
	}

	public void setTelefonePessoaSelecionado(Telefone telefonePessoaSelecionado) {
		this.telefonePessoaSelecionado = telefonePessoaSelecionado;
	}
	
	public String getCalculaIdade(Timestamp dataNascimento) {

		LocalDate hoje = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		LocalDate localDateNascimento = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
		 
		return String.valueOf(Period.between(localDateNascimento, hoje).getYears());
		
	}
	
	public void salvarPessoa() {
		PessoaController pessoaController = new PessoaController();
		pessoaController.salvar(pessoaCadastro);
		preparaListar();
	}
	
	public void pesquisarPessoa() {
		PessoaController pessoaController = new PessoaController();
		this.listaPessoa = pessoaController.pesquisar(pessoaCadastro);
		this.pessoaCadastro = new Pessoa();
	}
	
	public void preparaListar() {
		try {
			this.pessoaCadastro = new Pessoa();
			PessoaController pessoaController = new PessoaController();
			this.listaPessoa = pessoaController.listar();
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarPessoas.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void preparaCadastrar() {
		try {
			this.pessoaCadastro = new Pessoa();
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarPessoas.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void preparaEditar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarPessoas.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void deletarPessoa() {
		
		PessoaController pessoaController = new PessoaController();
		pessoaController.remover(this.pessoaSelecionada);
		this.listaPessoa.remove(this.pessoaSelecionada);
		
	}
	
	public void adicionarTelefonePessoa() {
		
		if (null == pessoaCadastro.getTelefones()) {
			
			pessoaCadastro.setTelefones(new ArrayList<Telefone>());
		}
		this.novoTelefone.setPessoa(pessoaCadastro);
		pessoaCadastro.getTelefones().add(this.novoTelefone);
		this.novoTelefone = new Telefone();
		
	}
	
	public void deletarTelefonePessoa() {
		
		pessoaCadastro.getTelefones().remove(telefonePessoaSelecionado);
		
	}
 	
}