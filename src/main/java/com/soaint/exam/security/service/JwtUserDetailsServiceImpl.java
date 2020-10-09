package com.soaint.exam.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soaint.exam.security.model.UserFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class JwtUserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserFile> listUserFile;
        try {
            listUserFile = Arrays.asList(new ObjectMapper().readValue(new ClassPathResource("./static/data.json").getFile(), UserFile[].class))
                    .stream().filter(p -> p.getUsername().contains(username)).collect(Collectors.toList());
        } catch(IOException e) {
            listUserFile = new ArrayList<>();
        }
        if (listUserFile.stream().count() == 0) {
            log.error("El usuario: {} no existe en el sistema", username);
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new User(listUserFile.get(0).getUsername(), listUserFile.get(0).getPassword(), new ArrayList<>());
        }
    }
}
