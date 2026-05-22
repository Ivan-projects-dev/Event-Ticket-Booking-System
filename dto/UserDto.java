package dto;
import domain.User;

public class UserDto {
    private String id;
    private String name;
    private String email;
    private boolean admin;
    private String createdAt;

    public UserDto() {
    }

    public UserDto(String id, String name, String email, boolean admin, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.admin = admin;
        this.createdAt = createdAt;
    }

    // Factory method: isAdmin computed by caller (from AdminRepository)
    public static UserDto from(User user, boolean isAdmin) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), isAdmin, user.getCreatedAt());
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}