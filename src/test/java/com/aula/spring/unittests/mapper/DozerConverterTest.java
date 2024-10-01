package com.aula.spring.unittests.mapper;

import com.aula.spring.person.dto.v2.PersonDTO;
import com.aula.spring.person.mapper.DozerMapper;
import com.aula.spring.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DozerConverterTest {

    MockPerson inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToDTOTest(){
        PersonDTO output = DozerMapper.parseObj(inputObject.mockEntity(), PersonDTO.class);
        assertEquals(Long.valueOf(0L),output.getKey());
        assertEquals("Test First Name0", output.getFirstName());
        assertEquals("Test Last Name0", output.getLastName());
        assertEquals("Test Address0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToDTOListTest(){
        List<PersonDTO> outputList = DozerMapper.parseListObj(inputObject.mockEntityList(), PersonDTO.class);
            PersonDTO outputZero = outputList.get(0);
            assertEquals(Long.valueOf(0L), outputZero.getKey());
            assertEquals("Test First Name0", outputZero.getFirstName());
            assertEquals("Test Last Name0", outputZero.getLastName());
            assertEquals("Test Address0", outputZero.getAddress());
            assertEquals("Male", outputZero.getGender());

            PersonDTO outputSeven = outputList.get(7);
            assertEquals(Long.valueOf(7L), outputSeven.getKey());
            assertEquals("Test First Name7", outputSeven.getFirstName());
            assertEquals("Test Last Name7", outputSeven.getLastName());
            assertEquals("Test Address7", outputSeven.getAddress());
            assertEquals("Female", outputSeven.getGender());

            PersonDTO outputTwelve = outputList.get(12);
            assertEquals(Long.valueOf(12L), outputTwelve.getKey());
            assertEquals("Test First Name12", outputTwelve.getFirstName());
            assertEquals("Test Last Name12", outputTwelve.getLastName());
            assertEquals("Test Address12", outputTwelve.getAddress());
            assertEquals("Male", outputTwelve.getGender());

    }


}
