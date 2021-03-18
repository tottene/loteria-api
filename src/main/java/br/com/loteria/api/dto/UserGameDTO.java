package br.com.loteria.api.dto;

public class UserGameDTO {
	
	private String email;
	
	private String numbers;

	public UserGameDTO(String email, String numbers) {
		this.email = email;
		this.numbers = numbers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
}
