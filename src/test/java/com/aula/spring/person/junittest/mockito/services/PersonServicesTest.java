package com.aula.spring.person.junittest.mockito.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

import com.aula.spring.exception.RequiredObjectIsNullException;
import com.aula.spring.person.dto.v2.PersonDTO;
import com.aula.spring.person.entity.Person;
import com.aula.spring.person.repositories.PersonRepository;
import com.aula.spring.person.services.PersonServices;
import com.aula.spring.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices services;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() throws Exception {
        Person persisted = input.mockEntity(1);
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.save(any(Person.class))).thenReturn(persisted);

        PersonDTO result = services.create(dto);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Test First Name1",result.getFirstName());
        assertEquals("Test Last Name1",result.getLastName());
        assertEquals("Test Address1",result.getAddress());
        assertEquals("Female",result.getGender());
    }

    @Test
    void createWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            services.create(null);
        });

        String expectedMessage ="It's not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() throws Exception {
        var entity = input.mockEntity(1);

        Person persisted = entity;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(any(Person.class))).thenReturn(persisted);

        PersonDTO result = services.update(dto);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Test First Name1",result.getFirstName());
        assertEquals("Test Last Name1",result.getLastName());
        assertEquals("Test Address1",result.getAddress());
        assertEquals("Female",result.getGender());
    }

    @Test
    void updateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            services.update(null);
        });

        String expectedMessage ="It's not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        services.delete(1L);
    }

    @Test
    void findById() throws Exception {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = services.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Test First Name1",result.getFirstName());
        assertEquals("Test Last Name1",result.getLastName());
        assertEquals("Test Address1",result.getAddress());
        assertEquals("Female",result.getGender());
    }

    @Test
    void findAll() {
        List<Person> entityList = input.mockEntityList();

        when(repository.findAll()).thenReturn(entityList);

        var people = services.findAll();

        assertNotNull(people);
        assertEquals(15, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertTrue(personOne.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals("Test First Name1",personOne.getFirstName());
        assertEquals("Test Last Name1",personOne.getLastName());
        assertEquals("Test Address1",personOne.getAddress());
        assertEquals("Female",personOne.getGender());

        var personSix = people.get(6);
        assertNotNull(personSix);
        assertNotNull(personSix.getKey());
        assertNotNull(personSix.getLinks());
        assertTrue(personSix.toString().contains("links: [</person/6>;rel=\"self\"]"));
        assertEquals("Test First Name6",personSix.getFirstName());
        assertEquals("Test Last Name6",personSix.getLastName());
        assertEquals("Test Address6",personSix.getAddress());
        assertEquals("Male",personSix.getGender());

        var personNine = people.get(9);
        assertNotNull(personNine);
        assertNotNull(personNine.getKey());
        assertNotNull(personNine.getLinks());
        assertTrue(personNine.toString().contains("links: [</person/9>;rel=\"self\"]"));
        assertEquals("Test First Name9",personNine.getFirstName());
        assertEquals("Test Last Name9",personNine.getLastName());
        assertEquals("Test Address9",personNine.getAddress());
        assertEquals("Female",personNine.getGender());

        var personFourteen = people.get(14);
        assertNotNull(personFourteen);
        assertNotNull(personFourteen.getKey());
        assertNotNull(personFourteen.getLinks());
        assertTrue(personFourteen.toString().contains("links: [</person/14>;rel=\"self\"]"));
        assertEquals("Test First Name14",personFourteen.getFirstName());
        assertEquals("Test Last Name14",personFourteen.getLastName());
        assertEquals("Test Address14",personFourteen.getAddress());
        assertEquals("Male",personFourteen.getGender());
    }
}