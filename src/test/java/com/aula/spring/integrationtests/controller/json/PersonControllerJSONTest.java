package com.aula.spring.integrationtests.controller.json;

import com.aula.spring.configs.TestConfigs;
import com.aula.spring.integrationtests.dto.AccountCredentialsDTO;
import com.aula.spring.integrationtests.dto.PersonDTOTest;
import com.aula.spring.integrationtests.dto.TokenDTO;
import com.aula.spring.integrationtests.testcontainer.AbstractIntegrationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.RequestBuilder;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class PersonControllerJSONTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;

	private static PersonDTOTest person;

	@BeforeAll
	public static void setup(){
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonDTOTest();
	}

	@Test
	@Order(1)
	public void authorization() throws JsonProcessingException {
		AccountCredentialsDTO user = new AccountCredentialsDTO("flavio", "admin4321");

		var accessToken = given()
				.basePath("auth/singin")
					.port(TestConfigs.SERVER_PORT)
					.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.body(user)
					.when()
				.post()
					.then()
						.statusCode(200)
							.extract()
							.body()
								.as(TokenDTO.class)
							.getAccessToken();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
				.setBasePath("/api/person")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
	}

		@Test
	@Order(2)
	public void testCreate() throws JsonProcessingException {
		mockPerson();

		var content =
			given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.header(TestConfigs.HEADER_PARAM_ORIGIN, "httpl://localhost:8080")
					.body(person)
					.when()
					.post()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();

		PersonDTOTest createdPerson = objectMapper.readValue(content, PersonDTOTest.class);
		person = createdPerson;

		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getKey());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());

		assertTrue(createdPerson.getKey() > 0);

		assertEquals("Test First Name", createdPerson.getFirstName());
		assertEquals("Test Last Name", createdPerson.getLastName());
		assertEquals("Test Address", createdPerson.getAddress());
		assertEquals("Test Gender", createdPerson.getGender());
	}

	@Test
	@Order(3)
	public void testFindById() throws JsonProcessingException {
		mockPerson();

		var content =
				given().spec(specification)
						.contentType(TestConfigs.CONTENT_TYPE_JSON)
						.header(TestConfigs.HEADER_PARAM_ORIGIN, "httpl://localhost:8080")
						.pathParams("id", person.getKey())
							.when()
								.get("{key}")
							.then()
								.statusCode(200)
							.extract()
								.body()
									.asString();

		PersonDTOTest createdPerson = objectMapper.readValue(content, PersonDTOTest.class);
		person = createdPerson;

		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getKey());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());

		assertTrue(createdPerson.getKey() < 0);

		assertEquals("Test First Name", createdPerson.getFirstName());
		assertEquals("Test Last Name", createdPerson.getLastName());
		assertEquals("Test Address", createdPerson.getAddress());
		assertEquals("Test Gender", createdPerson.getGender());
	}

	private void mockPerson() {
		person.setFirstName("Test First Name");
		person.setLastName("Test Last Name");
		person.setAddress("Test Address");
		person.setGender("Test Gender");
	}

}
