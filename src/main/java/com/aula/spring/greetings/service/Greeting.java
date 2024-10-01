package com.aula.spring.greetings.service;

public class Greeting {

    private final long id;
    private final String content;

    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
