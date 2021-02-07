package com.ofd.test.ofd.repositories.inter;

import com.ofd.test.ofd.entites.dto.UserDto;
import org.springframework.lang.Nullable;

import java.sql.SQLException;

public interface UserRepository {

    public void getAll();

    @Nullable
    UserDto findOneByName(String username) throws SQLException;

    void createNewUser(UserDto userDto) throws SQLException;

    Integer getBalance(String name) throws SQLException;
}
