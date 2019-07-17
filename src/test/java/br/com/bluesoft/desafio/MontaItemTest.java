package br.com.bluesoft.desafio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Item;
import br.com.bluesoft.desafio.model.Preco;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.dto.FornecedorComPrecosDTO;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.service.CriaPedidoItem;

public class MontaItemTest {

	private FornecedorRepository fornecedorRepository;
	private Produto cocaCola;

	@Before
	public void setUp() {
		cocaCola = criaCocaCola();

		List<FornecedorComPrecosDTO> fornecedoresDTO = new ArrayList<>();
		fornecedoresDTO.add(mockaFornecedorComPrecosDTO());

		fornecedorRepository = mock(FornecedorRepository.class);
		when(fornecedorRepository.findOne("56.918.868/0001-20")).thenReturn(mockaFornecedor());
		when(fornecedorRepository.save(mockaFornecedor())).thenReturn(mockaFornecedor());

	}

	private Produto criaCocaCola() {
		Produto produto = new Produto();
		produto.setGtin("7894900011517");
		produto.setNome("REFRIGERANTE COCA-COLA 2LT");
		return produto;
	}

	private Fornecedor mockaFornecedor() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj("56.918.868/0001-20");
		fornecedor.setNome("Fornecedor 1");
		return fornecedor;
	}

	private FornecedorComPrecosDTO mockaFornecedorComPrecosDTO() {
		FornecedorComPrecosDTO fornecedor = new FornecedorComPrecosDTO();
		fornecedor.setCnpj("56.918.868/0001-20");
		fornecedor.setNome("Fornecedor 1");
		fornecedor.setPreco(mockaPrecos());
		return fornecedor;
	}

	private List<Preco> mockaPrecos() {
		List<Preco> precos = new ArrayList<>();
		Preco preco1 = criaPreco(10.0, 1);
		Preco preco2 = criaPreco(8.0, 5);
		precos.add(preco1);
		precos.add(preco2);
		return precos;
	}

	private Preco criaPreco(double valor, int qtd) {
		Preco preco1 = new Preco();
		preco1.setQuantidade_minima(qtd);
		preco1.setValor(new BigDecimal(valor));
		return preco1;
	}

	@Test
	public void deveEncontrarOMenorPreco() {
		CriaPedidoItem montaItem = new CriaPedidoItem(fornecedorRepository);
		Item item = montaItem.criar(cocaCola, 1);
		assertEquals(new BigDecimal(10.0), item.getPreco());
	}

	@Test
	public void devePararExecucaoSeNaoTiverFornecedor() {
		List<FornecedorComPrecosDTO> fornecedoresSemCnpjDTO = new ArrayList<>();
		FornecedorComPrecosDTO fornecedor = new FornecedorComPrecosDTO();
		fornecedor.setPreco(mockaPrecos());
		fornecedoresSemCnpjDTO.add(fornecedor);
		try {
			CriaPedidoItem montaItem = new CriaPedidoItem(fornecedorRepository);
			montaItem.criar(cocaCola, 1);
		} catch (Exception e) {
			System.out.println(e);
			verify(fornecedorRepository, never()).findOne(fornecedor.getCnpj());
		}

	}

}
