package com.example.person.controller;

import com.example.person.model.Person;
import com.example.person.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class controllerPerson {

	private final PersonService personService;


	@GetMapping
	public ResponseEntity<List<Person>> getPerson() throws Exception {
		return ResponseEntity.ok(personService.getAllPerson());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable("id") Long id) throws Exception {
		return ResponseEntity.ok(personService.getById(id));
	}

	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Person createPerson(@RequestBody Person person){
		return personService.addPerson(person);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Person updatePerson(@RequestBody Person person) throws Exception {
		return personService.updatePerson(person);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@PathVariable("id") Long id) throws Exception {
        personService.deletePerson(id);
    }
}
