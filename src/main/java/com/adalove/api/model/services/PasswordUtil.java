package com.adalove.api.model.services;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {



    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean checkPassword(String password, String hashed) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashed);
        return result.verified;
    }
}
