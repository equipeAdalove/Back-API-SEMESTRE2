package com.adalove.api.model.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilTest {

    @Test
    public void testHashPassword() {
        String password = "mySecurePassword123";
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Verifica se o hash não é nulo ou vazio
        assertNotNull(hashedPassword);
        assertFalse(hashedPassword.isEmpty());
    }

    @Test
    public void testCheckPassword_Success() {
        String password = "mySecurePassword123";
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Verifica se a senha corresponde ao hash
        assertTrue(PasswordUtil.checkPassword(password, hashedPassword));
    }

    @Test
    public void testCheckPassword_Failure() {
        String password = "mySecurePassword123";
        String wrongPassword = "wrongPassword";
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Verifica se uma senha incorreta não corresponde ao hash
        assertFalse(PasswordUtil.checkPassword(wrongPassword, hashedPassword));
    }
}
