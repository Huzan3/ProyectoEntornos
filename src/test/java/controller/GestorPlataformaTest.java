package controller;

import model.*;
import model.planes.Plan;
import model.planes.PlanAdvanced;
import model.planes.PlanBasic;
import model.planes.PlanPremium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorPlataformaTest {
    private GestorPlataforma gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorPlataforma();

        Plan basic = new PlanBasic();
        Plan advanced = new PlanAdvanced();
        Plan premium = new PlanPremium();

        gestor.registrarUsuario("Alice", "alice@mail.com", basic);
        gestor.registrarUsuario("Bob", "bob@mail.com", premium);
        gestor.registrarUsuario("Carlos", "carlos@mail.com", advanced);

        gestor.agregarVideojuego(new Videojuego("IndieGame", "Indie", 20, 'B'));
        gestor.agregarVideojuego(new Videojuego("ActionGame", "Acción", 100, 'A'));
        gestor.agregarVideojuego(new Videojuego("UltraGame", "RPG", 200, 'P'));
    }


    @Test
    void testRegistrarUsuarioExitoso() {
        boolean resultado = gestor.registrarUsuario("Diana", "diana@mail.com", new PlanBasic());
        assertTrue(resultado);
        assertNotNull(gestor.buscarUsuario("diana@mail.com"));
    }

    @Test
    void testRegistrarUsuarioCorreoDuplicado() {
        assertFalse(gestor.registrarUsuario("Alice2", "alice@mail.com", new PlanBasic()));
    }

    @Test
    void testListarUsuarios() {
        assertEquals(3, gestor.listarUsuarios().size());
    }


    @Test
    void testBuscarUsuarioExistente() {
        Usuario alice = gestor.buscarUsuario("alice@mail.com");
        assertNotNull(alice);
        assertEquals("Alice", alice.getNombreCompleto());
    }

    @Test
    void testBuscarUsuarioInexistente() {
        assertNull(gestor.buscarUsuario("noexiste@mail.com"));
    }

    @Test
    void testBuscarVideojuegoExistente() {
        Videojuego vj = gestor.buscarVideojuego("IndieGame");
        assertNotNull(vj);
        assertEquals("Indie", vj.getGenero());
    }

    @Test
    void testBuscarVideojuegoInexistente() {
        assertNull(gestor.buscarVideojuego("JuegoFantasma"));
    }

    @Test
    void testLimitePartidasBasic() {
        assertEquals("Éxito: Partida de IndieGame iniciada.",
                gestor.iniciarPartida("alice@mail.com", "IndieGame"));

        String resultado = gestor.iniciarPartida("alice@mail.com", "IndieGame");
        assertTrue(resultado.contains("Error: Límite de partidas"));
    }

    @Test
    void testLimitePartidasAdvanced() {
        gestor.agregarVideojuego(new Videojuego("IndieGame2", "Indie", 30, 'B'));
        gestor.agregarVideojuego(new Videojuego("IndieGame3", "Indie", 40, 'B'));

        assertEquals("Éxito: Partida de IndieGame iniciada.",
                gestor.iniciarPartida("carlos@mail.com", "IndieGame"));
        assertEquals("Éxito: Partida de IndieGame2 iniciada.",
                gestor.iniciarPartida("carlos@mail.com", "IndieGame2"));

        String resultado = gestor.iniciarPartida("carlos@mail.com", "IndieGame3");
        assertTrue(resultado.contains("Error: Límite de partidas"));
    }

    @Test
    void testPartidasSimultaneasPremium() {
        gestor.iniciarPartida("bob@mail.com", "IndieGame");
        gestor.iniciarPartida("bob@mail.com", "ActionGame");
        gestor.iniciarPartida("bob@mail.com", "UltraGame");

        Usuario bob = gestor.buscarUsuario("bob@mail.com");
        assertEquals(3, bob.getNumPartidasActivas());
    }


    @Test
    void testBasicNoPuedeAccederAdvanced() {
        String resultado = gestor.iniciarPartida("alice@mail.com", "ActionGame");
        assertTrue(resultado.contains("no incluye este videojuego"));
    }

    @Test
    void testBasicNoPuedeAccederPremium() {
        String resultado = gestor.iniciarPartida("alice@mail.com", "UltraGame");
        assertTrue(resultado.contains("no incluye este videojuego"));
    }

    @Test
    void testAdvancedNoPuedeAccederPremium() {
        String resultado = gestor.iniciarPartida("carlos@mail.com", "UltraGame");
        assertTrue(resultado.contains("no incluye este videojuego"));
    }

    @Test
    void testPremiumPuedeAccederATodo() {
        assertTrue(gestor.iniciarPartida("bob@mail.com", "IndieGame").contains("Éxito"));
        assertTrue(gestor.iniciarPartida("bob@mail.com", "ActionGame").contains("Éxito"));
        assertTrue(gestor.iniciarPartida("bob@mail.com", "UltraGame").contains("Éxito"));
    }


    @Test
    void testVelocidadInsuficiente() {
        gestor.agregarVideojuego(new Videojuego("HeavyIndie", "Indie", 100, 'B'));

        String resultado = gestor.iniciarPartida("alice@mail.com", "HeavyIndie");
        assertTrue(resultado.contains("Velocidad insuficiente"));
    }

    @Test
    void testVelocidadSuficiente() {
        gestor.agregarVideojuego(new Videojuego("LightIndie", "Indie", 50, 'B'));

        String resultado = gestor.iniciarPartida("alice@mail.com", "LightIndie");
        assertTrue(resultado.contains("Éxito"));
    }


    @Test
    void testFinalizarPartida() {
        gestor.iniciarPartida("alice@mail.com", "IndieGame");
        Usuario alice = gestor.buscarUsuario("alice@mail.com");
        assertEquals(1, alice.getNumPartidasActivas());

        boolean finalizado = gestor.finalizarPartida("alice@mail.com", "IndieGame");
        assertTrue(finalizado);
        assertEquals(0, alice.getNumPartidasActivas());
    }

    @Test
    void testFinalizarPartidaInexistente() {
        boolean finalizado = gestor.finalizarPartida("alice@mail.com", "JuegoQueNoTiene");
        assertFalse(finalizado);
    }

    @Test
    void testFinalizarYVolverAIniciar() {
        gestor.iniciarPartida("alice@mail.com", "IndieGame");
        gestor.finalizarPartida("alice@mail.com", "IndieGame");

        assertEquals(0, gestor.buscarUsuario("alice@mail.com").getNumPartidasActivas());

        String resultado = gestor.iniciarPartida("alice@mail.com", "IndieGame");
        assertTrue(resultado.contains("Éxito"));
    }


    @Test
    void testCambioPlanPermitiAcceso() {
        String res1 = gestor.iniciarPartida("alice@mail.com", "ActionGame");
        assertTrue(res1.contains("no incluye"));

        gestor.cambiarPlanUsuario("alice@mail.com", new PlanAdvanced());

        String res2 = gestor.iniciarPartida("alice@mail.com", "ActionGame");
        assertTrue(res2.contains("Éxito"));
    }

    @Test
    void testCambioPlanAumentaLimitePartidas() {
        gestor.iniciarPartida("alice@mail.com", "IndieGame");

        String resultado = gestor.iniciarPartida("alice@mail.com", "IndieGame");
        assertTrue(resultado.contains("Límite de partidas"));

        gestor.cambiarPlanUsuario("alice@mail.com", new PlanAdvanced());

        gestor.agregarVideojuego(new Videojuego("IndieGame2", "Indie", 30, 'B'));
        String resultado2 = gestor.iniciarPartida("alice@mail.com", "IndieGame2");
        assertTrue(resultado2.contains("Éxito"));
    }


    @Test
    void testIniciarPartidaUsuarioInexistente() {
        String resultado = gestor.iniciarPartida("noexiste@mail.com", "IndieGame");
        assertTrue(resultado.contains("Usuario no encontrado"));
    }


    @Test
    void testIniciarPartidaVideojuegoInexistente() {
        String resultado = gestor.iniciarPartida("alice@mail.com", "JuegoFantasma");
        assertTrue(resultado.contains("Videojuego no encontrado"));
    }
}