package persistence;

import domain.User;
import java.util.List;

public class UserRepository extends GenericJsonRepository<User> {
    private static final String FILE_PATH = "data/users.json";

    public UserRepository() {
        super(FILE_PATH, User.class);
    }

    public User findByEmail(String email) {
        List<User> users = findAll();

        for (User user : users) {
            if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }

        return null;
    }
}
