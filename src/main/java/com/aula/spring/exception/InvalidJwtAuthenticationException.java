package com.aula.spring.exception;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException{

    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
