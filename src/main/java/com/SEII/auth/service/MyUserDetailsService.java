package com.SEII.auth.service;

import java.util.ArrayList;
import java.util.Optional;

import com.SEII.auth.model.MyUserDetails;
import com.SEII.models.Person;
import com.SEII.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

  @Autowired
  PersonRepository personRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Person> person = personRepository.findByUsername(username);

    person.orElseThrow(()-> new UsernameNotFoundException("Not found: " + username));
    
    return person.map(MyUserDetails::new).get();
  }
}
