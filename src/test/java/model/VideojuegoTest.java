package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideojuegoTest {
    private Videojuego juegoBasic;
    private Videojuego juegoAdvanced;
    private Videojuego juegoPremium;

    @BeforeEach
    void setUp() {
        juegoBasic = new Videojuego("IndieGame", "Indie", 20, 'B');
        juegoAdvanced = new Videojuego("ActionGame", "Acción", 100, 'A');
        juegoPremium = new Videojuego("UltraGame", "RPG", 200, 'P');
    }

    @Test
    void testGetNombre() {
        assertEquals("IndieGame", juegoBasic.getNombre());
        assertEquals("ActionGame", juegoAdvanced.getNombre());
        assertEquals("UltraGame", juegoPremium.getNombre());
    }

    @Test
    void testGetGenero() {
        assertEquals("Indie", juegoBasic.getGenero());
        assertEquals("Acción", juegoAdvanced.getGenero());
        assertEquals("RPG", juegoPremium.getGenero());
    }

    @Test
    void testGetVelocidadMinima() {
        assertEquals(20, juegoBasic.getVelocidadMinima());
        assertEquals(100, juegoAdvanced.getVelocidadMinima());
        assertEquals(200, juegoPremium.getVelocidadMinima());
    }

    @Test
    void testGetCategoria() {
        assertEquals('B', juegoBasic.getCategoria());
        assertEquals('A', juegoAdvanced.getCategoria());
        assertEquals('P', juegoPremium.getCategoria());
    }
}