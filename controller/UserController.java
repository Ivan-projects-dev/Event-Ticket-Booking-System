package controller;
import domain.User;
import dto.UserDto;
import service.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    private final UserService userService = new UserService();

    public UserDto register(String name, String email, String password) {
        User user = userService.register(name, email, password);
        return UserDto.from(user, false);
    }

    public UserDto registerAdmin(String name, String email, String password) {
        User user = userService.registerAdmin(name, email, password);
        return UserDto.from(user, true);
    }

    public UserDto getUserById(String id) {
        User user = userService.findById(id);

        if (user == null) {
            return null;
        }
        return UserDto.from(user, userService.isAdmin(id));
    }

    public List<UserDto> getAllUsers() {
        return userService.findAll().stream().map(u -> UserDto.from(u, userService.isAdmin(u.getId()))).collect(Collectors.toList());
    }
}