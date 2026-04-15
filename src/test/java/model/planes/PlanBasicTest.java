package model.planes;

import model.Videojuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanBasicTest {
    private PlanBasic plan;
    private Videojuego juegoB;
    private Videojuego juegoA;
    private Videojuego juegoP;

    @BeforeEach
    void setUp() {
        plan = new PlanBasic();
        juegoB = new Videojuego("IndieGame", "Indie", 20, 'B');
        juegoA = new Videojuego("ActionGame", "Acción", 100, 'A');
        juegoP = new Videojuego("UltraGame", "RPG", 200, 'P');
    }

    @Test
    void testNombre() {
        assertEquals("Basic", plan.getNombre());
    }

    @Test
    void testVelocidadMaxima() {
        assertEquals(50, plan.getVelocidadMaxima());
    }

    @Test
    void testMaxPartidasSimultaneas() {
        assertEquals(1, plan.getMaxPartidasSimultaneas());
    }

    @Test
    void testTipoCatalogo() {
        assertEquals("Reducido", plan.getTipoCatalogo());
    }

    @Test
    void testPuedeAccederJuegoBasic() {
        assertTrue(plan.puedeAcceder(juegoB));
    }

    @Test
    void testNoPuedeAccederJuegoAdvanced() {
        assertFalse(plan.puedeAcceder(juegoA));
    }

    @Test
    void testNoPuedeAccederJuegoPremium() {
        assertFalse(plan.puedeAcceder(juegoP));
    }
}