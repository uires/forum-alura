package br.com.alura.forum.controllers.handlers.dto;

public class ErroValidationDTO {
	private String campo;
	private String erro;

	public ErroValidationDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
