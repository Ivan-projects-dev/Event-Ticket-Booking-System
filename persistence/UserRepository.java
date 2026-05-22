package persistence;
import domain.User;
import java.util.List;

public class UserRepository {
    private static final String FILE_PATH = "data/users.json";

    public List<User> findAll() {
        return JsonStorage.readList(FILE_PATH, User.class);
    }

    public User findById(int id) {
        List<User> users = findAll();

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
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

    public void save(User user) {
        List<User> users = findAll();

        if (user.getId() == 0) {
            user.setId(generateNextId(users));
            users.add(user);
        } 
        else {
            boolean updated = false;

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == user.getId()) {
                    users.set(i, user);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                users.add(user);
            }
        }
        JsonStorage.writeList(FILE_PATH, users);
    }

    public void deleteById(int id) {
        List<User> users = findAll();
        users.removeIf(user -> user.getId() == id);
        JsonStorage.writeList(FILE_PATH, users);
    }

    private int generateNextId(List<User> users) {
        int maxId = 0;

        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }

        return maxId + 1;
    }
}