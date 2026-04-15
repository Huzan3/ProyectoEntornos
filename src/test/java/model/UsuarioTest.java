package model;

import model.planes.PlanAdvanced;
import model.planes.PlanPremium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private Usuario usuario;
    private Videojuego juego1;
    private Videojuego juego2;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Alice", "alice@mail.com", new PlanAdvanced());
        juego1 = new Videojuego("IndieGame", "Indie", 20, 'B');
        juego2 = new Videojuego("ActionGame", "Acción", 100, 'A');
    }

    @Test
    void testGetNombreCompleto() {
        assertEquals("Alice", usuario.getNombreCompleto());
    }

    @Test
    void testGetCorreo() {
        assertEquals("alice@mail.com", usuario.getCorreo());
    }

    @Test
    void testGetPlan() {
        assertNotNull(usuario.getPlan());
        assertEquals("Advanced", usuario.getPlan().getNombre());
    }

    @Test
    void testPartidasActivasInicialmenteCero() {
        assertEquals(0, usuario.getNumPartidasActivas());
    }

    @Test
    void testAgregarPartida() {
        Partida partida = new Partida(usuario, juego1);
        usuario.agregarPartida(partida);
        assertEquals(1, usuario.getNumPartidasActivas());
    }

    @Test
    void testAgregarVariasPartidas() {
        usuario.agregarPartida(new Partida(usuario, juego1));
        usuario.agregarPartida(new Partida(usuario, juego2));
        assertEquals(2, usuario.getNumPartidasActivas());
    }

    @Test
    void testEliminarPartida() {
        usuario.agregarPartida(new Partida(usuario, juego1));
        assertEquals(1, usuario.getNumPartidasActivas());

        boolean eliminado = usuario.eliminarPartida("IndieGame");
        assertTrue(eliminado);
        assertEquals(0, usuario.getNumPartidasActivas());
    }

    @Test
    void testEliminarPartidaInexistente() {
        boolean eliminado = usuario.eliminarPartida("JuegoQueNoExiste");
        assertFalse(eliminado);
    }

    @Test
    void testCambiarPlan() {
        assertEquals("Advanced", usuario.getPlan().getNombre());
        usuario.setPlan(new PlanPremium());
        assertEquals("Premium", usuario.getPlan().getNombre());
        assertEquals(500, usuario.getPlan().getVelocidadMaxima());
        assertEquals(4, usuario.getPlan().getMaxPartidasSimultaneas());
    }

    @Test
    void testContarSoloPartidasActivas() {
        Partida p1 = new Partida(usuario, juego1);
        Partida p2 = new Partida(usuario, juego2);
        usuario.agregarPartida(p1);
        usuario.agregarPartida(p2);
        assertEquals(2, usuario.getNumPartidasActivas());

        p1.finalizar();
        assertEquals(1, usuario.getNumPartidasActivas());
    }
}