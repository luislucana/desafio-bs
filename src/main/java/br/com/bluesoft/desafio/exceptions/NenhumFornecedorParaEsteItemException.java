package br.com.bluesoft.desafio.exceptions;

public class NenhumFornecedorParaEsteItemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7355908627718528792L;

	public NenhumFornecedorParaEsteItemException() {

	}

	public NenhumFornecedorParaEsteItemException(String mensagem) {
		super(mensagem);
	}

}
