package br.com.padaria.exception;

public class EntidadeComPendencialException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EntidadeComPendencialException(String mensagam) {
		super(mensagam);
	}

}
