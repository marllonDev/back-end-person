package com.example.person.service;

import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    public List<Person> getAllPerson() throws Exception {
       List<Person> finded  = personRepository.findAll();
        if (finded.isEmpty()) {
            throw new Exception("No person found");
        }
        return finded;
    }

    public Person addPerson(Person person) {
        Person newPerson = new Person();
        return personRepository.save(person);
    }

    public Person updatePerson(Person person) throws Exception {
        Optional<Person> oldPerson = personRepository.findById(person.getId());
        if (oldPerson.isEmpty()) {
            throw new Exception("Person with id " + person.getId() + " does not exist");
        }
        return personRepository.save(person);
    }

    public Person getById(Long id) throws Exception{
        Optional<Person> findId = personRepository.findById(id);
        if(findId.isEmpty()){
            throw new Exception("Person not found");
        }
        return findId.get();
    }

    public void deletePerson(Long id) throws Exception{
        Optional<Person> findId = personRepository.findById(id);
        if (findId.isEmpty()){
            throw new Exception("Person not found");
        }
        personRepository.deleteById(findId.get().getId());
    }
}
