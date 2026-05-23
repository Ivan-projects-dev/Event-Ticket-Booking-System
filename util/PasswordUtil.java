package util;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordUtil {
    private static final SecureRandom RANDOM = new SecureRandom();

    // returns stored value in "saltHex:sha256Hex" format.
    public static String hash(String password) {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        String saltHex = bytesToHex(salt),
            hashHex = sha256(saltHex + password);
        return saltHex + ":" + hashHex;
    }

    public static boolean verify(String password, String stored) {
        if (stored == null || !stored.contains(":")) {
            return false;
        }
        String[] parts = stored.split(":", 2);
        String saltHex = parts[0], expectedHash = parts[1],
            actualHash = sha256(saltHex + password);
        return expectedHash.equals(actualHash);
    }

    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes("UTF-8"));
            return bytesToHex(digest);
        } 
        catch (Exception e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}