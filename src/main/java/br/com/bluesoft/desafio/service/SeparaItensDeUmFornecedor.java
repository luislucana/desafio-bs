package br.com.bluesoft.desafio.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Item;

@Component
public class SeparaItensDeUmFornecedor {

	//FIXME
	//Favor implementar este código
	//O objetivo é separar os itens por fornecedor.
	//Este método receberá todos os itens criados, e deverá separar em um mapa,
	//aonde a chave do Mapa é o fornecedor e a key é uma Lista com seus itens.
	//Lembrando que o item tem um Fornecedor em seu mapeamento.
	//Para facilitar, existe uma classe de teste que já testa este método.
	public Map<Fornecedor, List<Item>> separa(List<Item> itens) {
		return null;
	}

}
