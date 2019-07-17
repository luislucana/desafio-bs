package br.com.bluesoft.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Item;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.model.dto.PedidoDTO;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.repository.ProdutoRepository;

@Component
public class PedidoService {

	private CriaPedidoItem montaItem;
	private ProdutoRepository repository;
	private SeparaItensDeUmFornecedor separador;
	private PedidoRepository pedidoRepository;
	private EmailSenderService emailSenderService;

	private List<Item> itens = new ArrayList<>();

	@Autowired
	public PedidoService(CriaPedidoItem montaItem, ProdutoRepository repository, SeparaItensDeUmFornecedor separador,
			     PedidoRepository pedidoRepository, EmailSenderService emailSenderService) {
		this.montaItem = montaItem;
		this.repository = repository;
		this.separador = separador;
		this.pedidoRepository = pedidoRepository;
		this.emailSenderService = emailSenderService;
	}

	public Iterable<Pedido> montaPedido(List<PedidoDTO> pedidos) {
		List<Pedido> pedidosfinal = new ArrayList<>();

		for (PedidoDTO pedidoDTO : pedidos) {
			if(pedidoDTO.getQuantidade() == 0)  {
				continue;
			}
			Produto produto = repository.findByGtin(pedidoDTO.getGtin());
			Item item = montaItem.criar(produto, pedidoDTO.getQuantidade());
			itens.add(item);
		}
		Map<Fornecedor, List<Item>> itensDeUmFornecedor = separador.separa(itens);
		for (Map.Entry<Fornecedor, List<Item>> entry : itensDeUmFornecedor.entrySet()) {
			Fornecedor fornecedor = entry.getKey();
			List<Item> itensDesteFornecedor = entry.getValue();
			Pedido pedido = new Pedido();
			pedido.setFornecedor(fornecedor);
			pedido.getItens().addAll(itensDesteFornecedor);
			pedido = pedidoRepository.save(pedido);
			pedidosfinal.add(pedido);
		}
		itens.clear();

		emailSenderService.notificarFornecedores(pedidosfinal);
		return pedidosfinal;
	}

}
