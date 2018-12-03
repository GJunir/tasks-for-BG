package tasks.second.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tasks.second.model.User;
import tasks.second.repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Iterable<User> showAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UUID addUser(String firstName, String lastName, LocalDate birthday, String email, String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setPassword(new String(md.digest()));
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public User findUser(String email) {
        User user = userRepository.findUserByEmail(email);
            return user;
    }

    @Transactional
    public void deleteUser(UUID id) {
        userRepository.delete(id);
    }
}
