package model.planes;

import model.Videojuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanPremiumTest {
    private PlanPremium plan;
    private Videojuego juegoB;
    private Videojuego juegoA;
    private Videojuego juegoP;

    @BeforeEach
    void setUp() {
        plan = new PlanPremium();
        juegoB = new Videojuego("IndieGame", "Indie", 20, 'B');
        juegoA = new Videojuego("ActionGame", "Acción", 100, 'A');
        juegoP = new Videojuego("UltraGame", "RPG", 200, 'P');
    }

    @Test
    void testNombre() {
        assertEquals("Premium", plan.getNombre());
    }

    @Test
    void testVelocidadMaxima() {
        assertEquals(500, plan.getVelocidadMaxima());
    }

    @Test
    void testMaxPartidasSimultaneas() {
        assertEquals(4, plan.getMaxPartidasSimultaneas());
    }

    @Test
    void testTipoCatalogo() {
        assertEquals("Completo", plan.getTipoCatalogo());
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
    void testPuedeAccederJuegoPremium() {
        assertTrue(plan.puedeAcceder(juegoP));
    }
}