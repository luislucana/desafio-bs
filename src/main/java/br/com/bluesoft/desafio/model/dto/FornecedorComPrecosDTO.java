package br.com.bluesoft.desafio.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.bluesoft.desafio.model.Preco;

public class FornecedorComPrecosDTO {

	private String cnpj;
	private String nome;
	@JsonProperty("precos")
	List<Preco> preco;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Preco> getPreco() {
		return preco;
	}

	public void setPreco(List<Preco> preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "FornecedorComPrecosDTO [cnpj=" + cnpj + ", nome=" + nome + ", preco=" + preco + "]";
	}

}
