package com.ofd.test.ofd.services;

import com.ofd.test.ofd.entites.dto.UserDto;
import com.ofd.test.ofd.exceptions.UserNameBusyException;
import com.ofd.test.ofd.repositories.UserRepositorySQLite;
import com.ofd.test.ofd.repositories.inter.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepositorySQLite userRepositorySQLite, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepositorySQLite;
        this.passwordEncoder = passwordEncoder;
    }

    public void getAll(){
        userRepository.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = null;

        try {
            userDto = userRepository.findOneByName(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (userDto == null) {
            throw new UsernameNotFoundException("1234321");
        }

        return new org.springframework.security.core.userdetails.User(userDto.getLogin(),passwordEncoder.encode(userDto.getPassword()),new ArrayList<>());
    }

    public void createNewUser(UserDto userDto) throws UserNameBusyException, Exception {

            UserDto user = userRepository.findOneByName(userDto.getLogin());

            if (!(user == null)) {throw new UserNameBusyException("");}
            userRepository.createNewUser(userDto);

    }

    public Integer getUserBalance(String name) throws SQLException {

        return userRepository.getBalance(name);

    }
}