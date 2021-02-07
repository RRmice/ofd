package com.ofd.test.ofd.repositories;

import com.ofd.test.ofd.DatabaseConnection;
import com.ofd.test.ofd.entites.dto.UserDto;
import com.ofd.test.ofd.repositories.inter.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class UserRepositorySQLite implements UserRepository {

    private final DatabaseConnection dbConnection;

    @Autowired
    public UserRepositorySQLite(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void getAll() {

    }

    @Override
    public UserDto findOneByName(String login) throws SQLException {

        String request = "SELECT * FROM (users u) WHERE (u.login) = (?)";

        PreparedStatement preparedStatement = dbConnection.getPreparedStatement(request);
        preparedStatement.setString(1, login);

        ResultSet result = preparedStatement.executeQuery();

        UserDto userDto = new UserDto();
        if (result.next()) {

            userDto.setLogin(result.getString("login"));
            userDto.setPassword(result.getString("password"));

        } else {
            return null;
        }

        return userDto;
    }

    @Override
    public void createNewUser(UserDto userDto) throws SQLException {

        dbConnection.setAutoCommit(false);

        String request = "INSERT INTO users(login, password) values(?,?);";
       //      +   "INSERT INTO balance(user_id, balance) values(last_insert_rowid(),0);";

        PreparedStatement preparedStatement = dbConnection.getPreparedStatement(request);

        preparedStatement.setString(1, userDto.getLogin());
        preparedStatement.setString(2, userDto.getPassword());

        preparedStatement.execute();

        String request1 = "INSERT INTO balance(user_id, balance) values(last_insert_rowid(),0)";
        PreparedStatement preparedStatement1 = dbConnection.getPreparedStatement(request1);
        preparedStatement1.execute();

        dbConnection.setAutoCommit(true);

    }

    @Override
    public Integer getBalance(String userName) throws SQLException {

        String request = "SELECT b.balance FROM balance b INNER JOIN users u ON b.user_id = u.id WHERE u.login = (?)";

        PreparedStatement preparedStatement = dbConnection.getPreparedStatement(request);
        preparedStatement.setString(1, userName);

        ResultSet result = preparedStatement.executeQuery();

        UserDto userDto = new UserDto();
        if (result.next()) {
            return result.getInt("balance");
        }

        return null;

    }

}