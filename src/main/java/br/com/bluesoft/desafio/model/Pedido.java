package br.com.bluesoft.desafio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//FIXME
	//Qual mapeamento deve ser usado aqui?
	//É possível fazer vários pedidos para um fornecedor.
	//Sempre que buscarmos um pedido queremos saber seu fornecedor
	private Fornecedor fornecedor;

	//FIXME
	//Qual mapeamento deve ser usado aqui?
	//Um item é de um pedido, um pedido pode ter vários itens.
	//Muitas vezes queremos buscar o pedido sem trazer os itens, apenas os dados do pedido
	private List<Item> itens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
