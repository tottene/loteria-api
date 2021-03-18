package br.com.loteria.api.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="people")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    // Construtor padrao
	public Person() {
    }

	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="email")
	private String email;
	
	@Column(name="created_at", updatable = false)
	private LocalDateTime createdAt;

	@JsonIgnoreProperties(value = {"person"}, allowSetters = true)
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Game> games = new ArrayList<Game>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	@Override
	public int hashCode() {
		return (this.getId() == null) ? 0 : Integer.parseInt(this.getId().toString());
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)  return false;
		if (object instanceof Person){
			return ((Person) object).getId().equals(this.getId());
		}
		return false;
	}
}