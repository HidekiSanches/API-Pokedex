package com.api.pokedex.pokemon.service;


import com.api.pokedex.pokemon.repository.UserRepository;
import com.api.pokedex.pokemon.model._User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       _User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

       return User
               .builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .roles("USER")
               .build();
    }

    public _User save(_User user) {
        return repository.save(user);
    }
}
