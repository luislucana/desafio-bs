package br.com.bluesoft.desafio.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.dto.PedidoDTO;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoService montaPedido;
	@Autowired
	private PedidoRepository pedidoRepository;

	@PostMapping
	public Iterable<Pedido> novoPedido(@RequestBody List<PedidoDTO> pedidos) throws Exception {
		System.out.println(pedidos);
		// validando que existe pelo menos um item com quantidade. Se nenhum
		// item tiver quantidade o pedido nao deve ser criado
		boolean q = false;
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getQuantidade() > 0) {
				q = true;
			}
		}
		if (!q) {
			throw new Exception("Por favor, escolha pelo menos um item");
		}
		return montaPedido.montaPedido(pedidos);
	}

	@GetMapping
	public Iterable<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

}
