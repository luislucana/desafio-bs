package br.com.bluesoft.desafio;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.desafio.model.dto.FornecedorComPrecosDTO;

public class ApiExternaDeFornecedoresDeUmProdutoTest {

	private final String BASE_URL = "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev";

	@Test
	public void testaFornecedoresDaBudweiser() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<FornecedorComPrecosDTO>> fornecedorResponse = restTemplate.exchange(
				BASE_URL + "/produto/7891910000197", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<FornecedorComPrecosDTO>>() {
				});
		assertEquals(fornecedorResponse.getStatusCode(), HttpStatus.OK);
	}

}
