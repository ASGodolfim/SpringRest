package com.aula.spring.person.dto.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTOV1 {


    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonDTOV1(){}

    public PersonDTOV1(Long id, String firstName, String lastName, String address, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }
}
