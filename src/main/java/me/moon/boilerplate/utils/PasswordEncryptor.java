package me.moon.boilerplate.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;

public class PasswordEncryptor {
    @Value("${jwt.secret_key}")
    private static String SECRET_KEY;

    public static String encrypt(String password){
        String pwd = password + SECRET_KEY;
        return BCrypt.hashpw(pwd, BCrypt.gensalt());
    }

    public static boolean isMatch(String password, String salt){
        String pwd = password + SECRET_KEY;
        return BCrypt.checkpw(pwd, salt);
    }
}
