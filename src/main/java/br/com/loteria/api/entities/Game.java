package br.com.loteria.api.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="games")
public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    // Construtor padrao
	public Game() {
    }

	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="title")
	private String title;
	
	@Column(name="numbers")
	private String numbers;
	
	@Column(name="created_at", updatable = false)
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person = new Person();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public int hashCode() {
		return (this.getId() == null) ? 0 : Integer.parseInt(this.getId().toString());
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)  return false;
		if (object instanceof Game){
			return ((Game) object).getId().equals(this.getId());
		}
		return false;
	}
}