package br.com.loteria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loteria.api.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
