package util;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]{2,}$");
    private static final int MIN_PASSWORD_LEN = 6;
    private static final int MAX_NAME_LEN = 100;

    public static void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be empty.");
        if (name.length() > MAX_NAME_LEN)
            throw new IllegalArgumentException("Name is too long (max " + MAX_NAME_LEN + " characters).");
    }

    public static void validateEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email must not be empty.");
        if (!EMAIL_PATTERN.matcher(email.trim()).matches())
            throw new IllegalArgumentException("Invalid email address.");
        if (email.length() > 254)
            throw new IllegalArgumentException("Email is too long.");
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LEN)
            throw new IllegalArgumentException("Password must be at least " + MIN_PASSWORD_LEN + " characters.");
    }

    public static void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(fieldName + " must not be empty.");
    }
}