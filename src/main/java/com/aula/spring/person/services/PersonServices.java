package com.aula.spring.person.services;

import com.aula.spring.exception.RequiredObjectIsNullException;
import com.aula.spring.exception.UnsuportedException;
import com.aula.spring.person.controller.PersonController;
import com.aula.spring.person.dto.v1.PersonDTOV1;
import com.aula.spring.person.dto.v2.PersonDTO;
import com.aula.spring.person.entity.Person;
import com.aula.spring.person.mapper.DozerMapper;
import com.aula.spring.person.mapper.custom.PersonMapper;
import com.aula.spring.person.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper mapper;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonDTOV1 createV1 (PersonDTOV1 person){
        logger.info("Creating one Person");

        var entity = DozerMapper.parseObj(person, Person.class);
        var dto = DozerMapper.parseObj(repository.save(entity), PersonDTOV1.class);
        return dto;
    }
    public PersonDTO create (PersonDTO person) throws Exception {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Person");

        var entity = DozerMapper.parseObj(person,Person.class);
        var dto = DozerMapper.parseObj(repository.save(entity), PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public PersonDTO update (PersonDTO person) throws Exception {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Person");

        var entity = repository.findById(person.getId()).orElseThrow(
                () -> new UnsuportedException("Nothing Found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = DozerMapper.parseObj(repository.save(entity), PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public void delete (long id){
        logger.info("Deleting one Person");
        var entity = repository.findById(id).orElseThrow(
                () -> new UnsuportedException("Nothing Found"));
        repository.delete(entity);
    }

    public PersonDTO findById(long id) throws Exception {

        logger.info("Finding one person!");
        var entity = repository.findById(id).orElseThrow(() -> new UnsuportedException("Nothing Found"));
        PersonDTO dto = DozerMapper.parseObj(entity, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }
    @Transactional
    public PersonDTO disablePerson (long key) throws Exception {

        logger.info("Disabling one person!");

        repository.disablePerson(key);

        var entity = repository.findById(key).orElseThrow(() -> new UnsuportedException("Nothing Found"));
        PersonDTO dto = DozerMapper.parseObj(entity, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(key)).withSelfRel());
        return dto;
    }


    public List<PersonDTO> findAll(){

        logger.info("Finding all the people!");

        var persons = DozerMapper.parseListObj(repository.findAll(), PersonDTO.class);
        persons.forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return persons;
    }
}
