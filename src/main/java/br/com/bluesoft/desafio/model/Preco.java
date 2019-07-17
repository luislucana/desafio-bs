package br.com.bluesoft.desafio.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Preco {

	@JsonProperty("preco")
	private BigDecimal valor;
	private int quantidade_minima;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidade_minima() {
		return quantidade_minima;
	}

	public void setQuantidade_minima(int quantidade_minima) {
		this.quantidade_minima = quantidade_minima;
	}

	@Override
	public String toString() {
		return "Preco [valor=" + valor + ", quantidade_minima=" + quantidade_minima + "]";
	}

}
