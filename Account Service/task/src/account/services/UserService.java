package account.services;

import account.model.User;
import account.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    public Optional<User> findUserByLastname(String lastname) {
        return userRepository.findUserByLastname(lastname);
    }
}
