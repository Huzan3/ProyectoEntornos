package model;

import model.planes.PlanBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {
    private Usuario usuario;
    private Videojuego videojuego;
    private Partida partida;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Alice", "alice@mail.com", new PlanBasic());
        videojuego = new Videojuego("IndieGame", "Indie", 20, 'B');
        partida = new Partida(usuario, videojuego);
    }

    @Test
    void testPartidaActivaAlCrear() {
        assertTrue(partida.isActiva());
    }

    @Test
    void testGetUsuario() {
        assertEquals(usuario, partida.getUsuario());
        assertEquals("Alice", partida.getUsuario().getNombreCompleto());
    }

    @Test
    void testGetVideojuego() {
        assertEquals(videojuego, partida.getVideojuego());
        assertEquals("IndieGame", partida.getVideojuego().getNombre());
    }

    @Test
    void testFinalizarPartida() {
        partida.finalizar();
        assertFalse(partida.isActiva());
    }

    @Test
    void testFinalizarPartidaDosVeces() {
        partida.finalizar();
        partida.finalizar();
        assertFalse(partida.isActiva());
    }
}