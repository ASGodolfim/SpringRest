package com.aula.spring.integrationtests.controller.json;


import com.aula.spring.configs.TestConfigs;
import com.aula.spring.integrationtests.dto.AccountCredentialsDTO;
import com.aula.spring.integrationtests.dto.TokenDTO;
import com.aula.spring.integrationtests.testcontainer.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerJSONTest extends AbstractIntegrationTest {

    private static TokenDTO tokenDTO;

    @Test
    @Order(1)
    public void testSignIn() throws JsonProcessingException {
        AccountCredentialsDTO user = new AccountCredentialsDTO("flavio", "admin4321");

         tokenDTO = given()
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
                                .as(TokenDTO.class);

         assertNotNull(tokenDTO.getAccessToken());
         assertNotNull(tokenDTO.getRefreshToken());
    }

    @Test
    @Order(2)
    public void testRefresh() throws JsonProcessingException {
        AccountCredentialsDTO user = new AccountCredentialsDTO("flavio", "admin4321");

        var tokenDTO2 = given()
                .basePath("auth/singin")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .pathParam("username", tokenDTO.getUsername())
                    .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenDTO.getRefreshToken())
                .when()
                    .put("{username}")
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .as(TokenDTO.class);

        assertNotNull(tokenDTO2.getAccessToken());
        assertNotNull(tokenDTO2.getRefreshToken());
    }

}
