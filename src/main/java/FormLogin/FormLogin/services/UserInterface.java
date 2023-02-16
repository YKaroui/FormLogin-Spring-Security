package onetech.onetech.services;

import java.util.List;

import onetech.onetech.entities.User;

public interface UserInterface {
    User add(User user);

    void delete(User user);

    User retrieveByEmail(String email);

    List<User> retrieveAll();
}
