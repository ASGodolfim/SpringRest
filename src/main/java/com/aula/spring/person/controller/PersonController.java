package com.aula.spring.person.controller;

import com.aula.spring.person.dto.v1.PersonDTOV1;
import com.aula.spring.person.dto.v2.PersonDTO;
import com.aula.spring.person.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api/person")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {

    @Autowired
    private PersonServices services;

    @PostMapping(value = "/v1",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Old Create")
    public PersonDTOV1 createV1(@RequestBody PersonDTOV1 person) throws Exception{
        return services.createV1(person);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Creates a new person", description = "Creates a new person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request Error", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Error", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonDTO create(@RequestBody PersonDTO person) throws Exception{
        return services.create(person);
    }

    @PutMapping(value = "{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Updates a person", description = "Updatess a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request Error", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Error", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found Error", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonDTO update(@RequestBody PersonDTO person) throws Exception{
        return services.update(person);
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds a person", description = "Finds a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                            @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request Error", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Error", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found Error", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonDTO findById(@PathVariable(value = "id") long id) throws Exception{
        return services.findById(id);
    }

    @PatchMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Disables a person", description = "Finds a person, then disable it",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request Error", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Error", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found Error", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public PersonDTO disablePerson(@PathVariable(value = "id") long id) throws Exception{
        return services.disablePerson(id);
    }


    @GetMapping(value = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all people", description = "Finds all people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request Error", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Error", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found Error", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public List<PersonDTO> findAll(){
        return services.findAll();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a person", description = "Deletes a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request Error", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Error", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found Error", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) throws Exception{
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
