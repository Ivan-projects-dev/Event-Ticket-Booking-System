package dto;

import domain.User;
import domain.UserRole;

public class UserDto {
    private String id;
    private String name;
    private String email;
    private UserRole role;
    private String createdAt;

    public UserDto() {
    }

    public UserDto(String id, String name, String email, UserRole role, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Factory method: isAdmin computed by caller (from AdminRepository)
    public static UserDto from(User user, boolean isAdmin) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(),
                isAdmin ? UserRole.ADMIN : UserRole.USER, user.getCreatedAt());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
