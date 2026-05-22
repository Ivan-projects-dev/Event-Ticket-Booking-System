package persistence;
import domain.Admin;
import java.util.List;

public class AdminRepository extends GenericJsonRepository<Admin> {
    private static final String FILE_PATH = "data/admins.json";

    public AdminRepository() {
        super(FILE_PATH, Admin.class);
    }

    public Admin findByUserId(String userId) {
        List<Admin> admins = findAll();

        for (Admin admin : admins) {
            if (admin.getUserId() != null && admin.getUserId().equals(userId)) {
                return admin;
            }
        }
        return null;
    }

    public boolean isAdmin(String userId) {
        return findByUserId(userId) != null;
    }
}