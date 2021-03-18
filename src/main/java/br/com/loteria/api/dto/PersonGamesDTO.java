package br.com.loteria.api.dto;

import java.util.List;

public class PersonGamesDTO {
	
	private String email;
	
	private List<GameAttributes> games;
	
	public PersonGamesDTO(String email, List<GameAttributes> games) {
		this.email = email;
		this.games = games;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<GameAttributes> getGames() {
		return games;
	}

	public void setGames(List<GameAttributes> games) {
		this.games = games;
	}
}
