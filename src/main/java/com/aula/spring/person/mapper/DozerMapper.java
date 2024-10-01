package com.aula.spring.person.mapper;

import com.aula.spring.person.dto.v2.PersonDTO;
import com.aula.spring.person.entity.Person;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(Person.class, PersonDTO.class)
                .addMapping(Person::getId, PersonDTO::setId);
        mapper.createTypeMap(PersonDTO.class, Person.class)
                .addMapping(PersonDTO::getId, Person::setId);
    }

    public static <O, D> D parseObj(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObj(List<O> origin, Class<D> destination){

        List<D> destinationObj = new ArrayList<D>();
        for(O o: origin){
            destinationObj.add(mapper.map(o, destination));
        }
        return destinationObj;
    }
}
