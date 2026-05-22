package service;
import domain.User;
import domain.UserRole;
import persistence.UserRepository;
import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public User register(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use: " + email);
        }

        User user = new User(0, email, password, name, UserRole.USER);
        userRepository.save(user);
        return user;
    }

    public User registerAdmin(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use: " + email);
        }

        User user = new User(0, email, password, name, UserRole.ADMIN);
        userRepository.save(user);
        return user;
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}