package br.com.loteria.api.resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.loteria.api.dto.GameAttributes;
import br.com.loteria.api.dto.PersonGameDTO;
import br.com.loteria.api.dto.PersonGamesDTO;
import br.com.loteria.api.entities.Game;
import br.com.loteria.api.entities.Person;
import br.com.loteria.api.repositories.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonResource {
	
	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public ResponseEntity<?> index() {
		List<Person> people = personRepository.findAll();
		
		return !people.isEmpty() ? ResponseEntity.ok(people) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/by-person")
	public ResponseEntity<?> show(@RequestParam String email) {
		// Carrego a pessoa do banco pegando o email passado na requisição
		Person person = personRepository.findByEmail(email);
		
		// Caso o e-mail não exista, devolvo uma resposta amigável
		if (person == null) {
			return ResponseEntity.status(HttpStatus.OK).body("E-mail not found");
		}
		
		// Criou uma lista auxiliar para preencher o DTO do jogos da pessoa
		List<GameAttributes> games = new ArrayList<GameAttributes>();
		int index = 1;
		for (Game eachGame : person.getGames()) {
			GameAttributes attr = new GameAttributes();
			attr.setOrder(index);
			attr.setNumbers(eachGame.getNumbers());
			attr.setCreatedAt(eachGame.getCreatedAt());
			games.add(attr);
			index++;
		}
		
		// Aqui eu utilizo o DTO dos jogos da Pessoa criando uma resposta amigável
		PersonGamesDTO personGames = new PersonGamesDTO(person.getEmail(), games);
		
		return ResponseEntity.status(HttpStatus.OK).body(personGames);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestParam String email, HttpServletResponse response) {

		// Utilizei o pattern regex para verificação de e-mail
		Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
		// Função para validar e-mail e devolver uma resposta amigável na resposta
		boolean isValidEmail = ptr.matcher(email).matches();
		if (!isValidEmail) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid e-mail");
		}
		
		// Lista com todos os números da loteria de 1 a 60
		List<Integer> lotteryNumbers = new ArrayList<>();
		for (int i = 1; i <= 60; i++) lotteryNumbers.add(i);
        // Método utilizado para trocar a ordem da lista de forma aleatória
		Collections.shuffle(lotteryNumbers);
        
		// Lista para armazenar os números aletórios pegando as 6 primeiras posições da lista de números da loteria
        List<Integer> sortedNumbers = new ArrayList<>();
        for (int j = 0; j < 6; j++) sortedNumbers.add(lotteryNumbers.get(j));
        // Método utilizado para ordenar os números escolhidos
        Collections.sort(sortedNumbers);

        // Aqui pesquiso se já existe uma pessoa com o email cadastrado no banco
        // Se encontrar eu utilizo o objeto carregado Senão crio um novo objeto
        Person person = personRepository.findByEmail(email);
        if (person == null) person = new Person();
        
        person.setEmail(email);
        person.setCreatedAt(LocalDateTime.now());
        
        // O Jogo sempre será criado
        Game game = new Game();
        game.setTitle("GAME " + (int) Math.ceil((Math.random() * 10000)));
        game.setNumbers(sortedNumbers.toString().replace("[", "").replace("]", ""));
        game.setCreatedAt(LocalDateTime.now());
        game.setPerson(person);

        // Atribuo o novo jogo ao Pessoa
        person.getGames().add(game);
        
        // Aqui salvo as informações no banco seja um insert ou um update 
        // O JpaRepository entende se tem um objeto novo ou carregado do banco e 
        // já cria um jogo uma vez que foi setado Cascade.ALL na classe Person
        personRepository.save(person);
        
        // Aqui eu utilizo o DTO de jogo da Pessoa criando uma resposta amigável
        PersonGameDTO personGame = new PersonGameDTO(person.getEmail(), game.getNumbers());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(personGame);
	}
	

}
