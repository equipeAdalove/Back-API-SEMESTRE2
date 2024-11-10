package com.adalove.api.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(1, "user_test", "hashed_password", true);
    }

    @Test
    void testConstructorWithId() {
        assertEquals(1, usuario.getId());
        assertEquals("user_test", usuario.getUsername());
        assertEquals("hashed_password", usuario.getPassword());
        assertTrue(usuario.isAdministrador());
    }

    @Test
    void testConstructorWithoutId() {
        Usuario newUser = new Usuario("new_user", "new_hashed_password", false);

        assertEquals("new_user", newUser.getUsername());
        assertEquals("new_hashed_password", newUser.getPassword());
        assertFalse(newUser.isAdministrador());
    }

    @Test
    void testSetters() {
        usuario.setId(2);
        usuario.setUsername("updated_user");
        usuario.setPassword("updated_hashed_password");
        usuario.setAdministrador(false);

        assertEquals(2, usuario.getId());
        assertEquals("updated_user", usuario.getUsername());
        assertEquals("updated_hashed_password", usuario.getPassword());
        assertFalse(usuario.isAdministrador());
    }

    @Test
    void testToString() {
        String expectedString = "Usuario{id=1, username='user_test', administrador=true}";
        assertEquals(expectedString, usuario.toString());
    }
}
