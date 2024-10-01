package com.aula.spring.person.mapper.custom;

import com.aula.spring.person.dto.v2.PersonDTO;
import com.aula.spring.person.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    public PersonDTO convertEntityToDTO (Person person){
        PersonDTO dto = new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getGender(),
                person.getEnabled()
        );
        return dto;
    }

    public Person convertDTOToEntity (PersonDTO person){
        Person entity = new Person(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getGender(),
                person.getEnabled()
        );
        return entity;
    }
}
