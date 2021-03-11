package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persistence.Person;
import ru.geekbrains.persistence.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void insert(Person person) {
        personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

}
