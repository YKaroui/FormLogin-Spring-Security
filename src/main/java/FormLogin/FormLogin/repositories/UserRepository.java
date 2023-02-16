package onetech.onetech.repositories;

import org.springframework.data.repository.CrudRepository;

import onetech.onetech.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
