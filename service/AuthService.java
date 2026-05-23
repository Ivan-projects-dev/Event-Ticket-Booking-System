package service;
import domain.User;
import persistence.UserRepository;
import util.PasswordUtil;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null || !PasswordUtil.verify(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        return user;
    }
}