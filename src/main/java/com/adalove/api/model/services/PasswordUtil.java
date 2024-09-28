package com.adalove.api.model.services;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {


    // Método para gerar o hash da senha
    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    // Método para verificar a senha
    public static boolean checkPassword(String password, String hashed) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashed);
        return result.verified;
    }
}
