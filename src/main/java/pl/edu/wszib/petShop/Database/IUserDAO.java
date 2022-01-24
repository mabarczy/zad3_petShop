package pl.edu.wszib.petShop.Database;

import pl.edu.wszib.petShop.Model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
}
