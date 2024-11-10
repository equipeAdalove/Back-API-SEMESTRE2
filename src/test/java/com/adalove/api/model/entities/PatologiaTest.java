package com.adalove.api.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatologiaTest {

    private Patologia patologia;

    @BeforeEach
    void setUp() {
        patologia = new Patologia("A00", 2, "Cólera");
    }

    @Test
    void testConstructor() {
        assertEquals("A00", patologia.getCid());
        assertEquals(2, patologia.getGrau());
        assertEquals("Cólera", patologia.getNome());
    }

    @Test
    void testSetters() {
        patologia.setCid("B99");
        patologia.setGrau(3);
        patologia.setNome("Febre Hemorrágica");

        assertEquals("B99", patologia.getCid());
        assertEquals(3, patologia.getGrau());
        assertEquals("Febre Hemorrágica", patologia.getNome());
    }

    @Test
    void testToString() {
        String expectedString = "Patologia{cid=A00, grau=2, nome='Cólera'}";
        assertEquals(expectedString, patologia.toString());
    }
}
