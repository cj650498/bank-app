package com.calebjones.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    public static String hashPassword(String password) {
        // Generate a random salt
        String salt = BCrypt.gensalt();

        // Hash the password with the salt
        String hashedPassword = BCrypt.hashpw(password, salt);

        return hashedPassword;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        // Verify the entered password against the stored hashed password
        return BCrypt.checkpw(password, hashedPassword);
    }
}
