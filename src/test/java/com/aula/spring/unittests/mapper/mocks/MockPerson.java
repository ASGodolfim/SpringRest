package com.aula.spring.unittests.mapper.mocks;

import com.aula.spring.person.dto.v2.PersonDTO;
import com.aula.spring.person.entity.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockPerson {

    public Person mockEntity(){
        return mockEntity(0);
    }

    public PersonDTO mockDTO(){
        return mockDTO(0);
    }

    public List<Person> mockEntityList(){
        List<Person> persons = new ArrayList<Person>();
        for (int i=0;i<=14;i++){
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public Person mockEntity(Integer number){
        Person person = new Person(
                number.longValue(),
                "Test First Name" + number,
                "Test Last Name" + number,
                "Test Address" + number,
                ((number % 2) == 0 ? "Male" : "Female"),
                true);
        return person;
    }

    public PersonDTO mockDTO(Integer number){
        PersonDTO person = new PersonDTO(
                number.longValue(),
                "Test First Name" + number,
                "Test Last Name" + number,
                "Test Address" + number,
                ((number % 2) == 0 ? "Male" : "Female"),
                true);
                //new Date(number/number/number));
        return person;
    }
}
