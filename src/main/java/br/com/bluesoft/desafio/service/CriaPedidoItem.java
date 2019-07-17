package br.com.bluesoft.desafio.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.desafio.exceptions.NenhumFornecedorParaEsteItemException;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Item;
import br.com.bluesoft.desafio.model.Preco;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.dto.FornecedorComPrecosDTO;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.util.MathUtil;

@Component
public class CriaPedidoItem {

	private final String BASE_URL = "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev";
	private FornecedorRepository fornecedorRepository;

	@Autowired
	public CriaPedidoItem(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}

	public Item criar(Produto produto, int quantidade) {
		BigDecimal valorMaisBarato = new BigDecimal(Integer.MAX_VALUE);
		FornecedorComPrecosDTO fornecedorDTO = new FornecedorComPrecosDTO();
		List<FornecedorComPrecosDTO> fornecedoresDTO = buscaFornecedores(produto);
		for (FornecedorComPrecosDTO fornecedorComPrecosDTO : fornecedoresDTO) {
			List<Preco> precos = fornecedorComPrecosDTO.getPreco();
			for (Preco preco : precos) {
				if (quantidade >= preco.getQuantidade_minima() && MathUtil.ehMenor(preco.getValor(), valorMaisBarato)) {
					valorMaisBarato = preco.getValor();
					fornecedorDTO = fornecedorComPrecosDTO;
				}
			}
		}
		Fornecedor fornecedor = new Fornecedor();
		if (fornecedorDTO.getCnpj() == null) {
			throw new NenhumFornecedorParaEsteItemException(
					"Nenhum fornecedor encontrado para a quantidade solicitada do produto " + produto.getNome());
		}
		fornecedor.setCnpj(fornecedorDTO.getCnpj());
		fornecedor.setNome(fornecedorDTO.getNome());

		Fornecedor fornecedorGerenciado = fornecedorRepository.findOne(fornecedor.getCnpj());
		if (fornecedorGerenciado != null) {
			fornecedor = fornecedorGerenciado;
		} else {
			fornecedor = fornecedorRepository.save(fornecedor);
		}

		Item item = new Item();
		item.setPreco(valorMaisBarato);
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		BigDecimal total = valorMaisBarato.multiply(new BigDecimal(quantidade));
		item.setTotal(total);
		item.setFornecedor(fornecedor);
		return item;
	}

	private List<FornecedorComPrecosDTO> buscaFornecedores(Produto produto) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<FornecedorComPrecosDTO>> fornecedorResponse = restTemplate.exchange(
				BASE_URL + "/produto/" + produto.getGtin(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<FornecedorComPrecosDTO>>() {
				});
		List<FornecedorComPrecosDTO> fornecedoresDTO = fornecedorResponse.getBody();
		return fornecedoresDTO;
	}
	
}
