package com.aula.spring.person.services;


import com.aula.spring.person.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class UserServices implements UserDetailsService {


    @Autowired
    UserRepository repository;

    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    public UserServices(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;

        } else {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    }
}
