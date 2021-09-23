package anacsoares.crudapi.controllers;

import anacsoares.crudapi.entities.PersonEntity;
import anacsoares.crudapi.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {


    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        super();
        this.personRepository = personRepository;
    }

    @PostMapping
    public ResponseEntity<PersonEntity> save(@RequestBody PersonEntity personEntity) {
        personRepository.save(personEntity);
        return new ResponseEntity<>(personEntity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAll() {
       List<PersonEntity> personEntities = new ArrayList<>();
       personEntities = personRepository.findAll();
       return new ResponseEntity<>(personEntities, HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<PersonEntity>> getById(@PathVariable Integer id) {
        Optional<PersonEntity> personEntity;

        try {
            personEntity = personRepository.findById(id);
            return new ResponseEntity<Optional<PersonEntity>>(personEntity, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Optional<PersonEntity>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<PersonEntity>> deleteById(@PathVariable Integer id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<Optional<PersonEntity>>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Optional<PersonEntity>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonEntity> update(@PathVariable Integer id, @RequestBody PersonEntity newPersonEntity) {
        return personRepository.findById(id)
                .map(personEntity -> {
                    personEntity.setName(newPersonEntity.getName());
                    personEntity.setAge(newPersonEntity.getAge());
                    PersonEntity personUpdated = personRepository.save(personEntity);
                    return ResponseEntity.ok().body(personUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
