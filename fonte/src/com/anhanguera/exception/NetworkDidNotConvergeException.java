package com.anhanguera.exception;

public class NetworkDidNotConvergeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NetworkDidNotConvergeException() {
		super("A rede não conseguiu atingir um ponto de convergencia. Verifique os parametros e a estrutura da rede.");
	}

	public NetworkDidNotConvergeException(String message) {
		super(message);
	}

	public NetworkDidNotConvergeException(String message, Throwable cause) {
		super(message, cause);
	}
}
