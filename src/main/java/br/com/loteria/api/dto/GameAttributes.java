package br.com.loteria.api.dto;

import java.time.LocalDateTime;

public class GameAttributes {
	
	public GameAttributes() {
	}
	
	private Integer order;
	private String numbers;
	private LocalDateTime createdAt;

	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
