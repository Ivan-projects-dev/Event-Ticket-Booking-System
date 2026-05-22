package service;

import domain.Admin;
import domain.User;
import persistence.AdminRepository;
import persistence.UserRepository;
import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final AdminRepository adminRepository = new AdminRepository();

    public User register(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use: " + email);
        }

        String now = LocalDateTime.now().toString();
        User user = new User(null, email, password, name, now);
        userRepository.save(user);
        return user;
    }

    public User registerAdmin(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use: " + email);
        }

        String now = LocalDateTime.now().toString();
        User user = new User(null, email, password, name, now);
        userRepository.save(user);

        // Create a corresponding Admin record
        Admin admin = new Admin(null, user.getId(), now);
        adminRepository.save(admin);

        return user;
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean isAdmin(String userId) {
        return adminRepository.isAdmin(userId);
    }
}
