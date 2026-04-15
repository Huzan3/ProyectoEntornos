package model.planes;

import model.Videojuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanAdvancedTest {
    private PlanAdvanced plan;
    private Videojuego juegoB;
    private Videojuego juegoA;
    private Videojuego juegoP;

    @BeforeEach
    void setUp() {
        plan = new PlanAdvanced();
        juegoB = new Videojuego("IndieGame", "Indie", 20, 'B');
        juegoA = new Videojuego("ActionGame", "Acción", 100, 'A');
        juegoP = new Videojuego("UltraGame", "RPG", 200, 'P');
    }

    @Test
    void testNombre() {
        assertEquals("Advanced", plan.getNombre());
    }

    @Test
    void testVelocidadMaxima() {
        assertEquals(150, plan.getVelocidadMaxima());
    }

    @Test
    void testMaxPartidasSimultaneas() {
        assertEquals(2, plan.getMaxPartidasSimultaneas());
    }

    @Test
    void testTipoCatalogo() {
        assertEquals("Ampliado", plan.getTipoCatalogo());
    }

    @Test
    void testPuedeAccederJuegoBasic() {
        assertTrue(plan.puedeAcceder(juegoB));
    }

    @Test
    void testPuedeAccederJuegoAdvanced() {
        assertTrue(plan.puedeAcceder(juegoA));
    }

    @Test
    void testNoPuedeAccederJuegoPremium() {
        assertFalse(plan.puedeAcceder(juegoP));
    }
}