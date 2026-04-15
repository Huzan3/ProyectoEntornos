package controller;

import model.*;
import model.planes.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la plataforma de videojuegos, incluyendo usuarios, videojuegos y partidas.
 */
public class GestorPlataforma {
    private List<Usuario> usuarios;
    private List<Videojuego> videojuegos;

    /**
     * Constructor que inicializa las listas de usuarios y videojuegos.
     */
    public GestorPlataforma() {
        usuarios = new ArrayList<>();
        videojuegos = new ArrayList<>();
    }

    /**
     * Registra un nuevo usuario en la plataforma.
     *
     * @param nombre El nombre completo del usuario.
     * @param correo El correo electrónico del usuario (debe ser único).
     * @param plan   El plan de suscripción del usuario.
     * @return true si el usuario se registró correctamente, false si el correo ya existe.
     */
    public boolean registrarUsuario(String nombre, String correo, Plan plan) {
        if (buscarUsuario(correo) != null) {
            return false;
        }
        usuarios.add(new Usuario(nombre, correo, plan));
        return true;
    }

    /**
     * Agrega un nuevo videojuego a la plataforma.
     *
     * @param videojuego El videojuego a agregar.
     */
    public void agregarVideojuego(Videojuego videojuego) {
        videojuegos.add(videojuego);
    }

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo El correo electrónico del usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    public Usuario buscarUsuario(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Busca un videojuego por su nombre.
     *
     * @param nombre El nombre del videojuego a buscar.
     * @return El videojuego encontrado o null si no existe.
     */
    public Videojuego buscarVideojuego(String nombre) {
        for (Videojuego videojuego : videojuegos) {
            if (videojuego.getNombre().equalsIgnoreCase(nombre)) {
                return videojuego;
            }
        }
        return null;
    }

    /**
     * Lista todos los usuarios registrados en la plataforma.
     *
     * @return Una lista de usuarios.
     */
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    /**
     * Lista todos los videojuegos disponibles en la plataforma.
     *
     * @return Una lista de videojuegos.
     */
    public void cambiarPlanUsuario(String correo, Plan nuevoPlan) {
        Usuario usuario = buscarUsuario(correo);
        if (usuario != null) {
            usuario.setPlan(nuevoPlan);
        }
    }

    /**
     * Inicia una partida para un usuario con un videojuego específico.
     *
     * @param correoUsuario    El correo del usuario que desea iniciar la partida.
     * @param nombreVideojuego El nombre del videojuego que se desea jugar.
     * @return Un mensaje indicando el resultado de la operación.
     */
    public String iniciarPartida(String correoUsuario, String nombreVideojuego) {
        Usuario usuario = buscarUsuario(correoUsuario);
        if (usuario == null) {
            return "Error: Usuario no encontrado.";
        }

        Videojuego videojuego = buscarVideojuego(nombreVideojuego);
        if (videojuego == null) {
            return "Error: Videojuego no encontrado.";
        }

        if (usuario.getNumPartidasActivas() >= usuario.getPlan().getMaxPartidasSimultaneas()) {
            return "Error: Límite de partidas simultáneas alcanzado.";
        }

        if (!usuario.getPlan().puedeAcceder(videojuego)) {
            return "Error: Tu plan no incluye este videojuego.";
        }

        if (usuario.getPlan().getVelocidadMaxima() < videojuego.getVelocidadMinima()) {
            return "Error: Velocidad insuficiente para ejecutar el videojuego.";
        }

        Partida partida = new Partida(usuario, videojuego);
        usuario.agregarPartida(partida);

        return "Éxito: Partida de " + videojuego.getNombre() + " iniciada.";
    }

    /**
     * Finaliza una partida activa de un usuario para un videojuego específico.
     *
     * @param correoUsuario    El correo del usuario que desea finalizar la partida.
     * @param nombreVideojuego El nombre del videojuego cuya partida se desea finalizar.
     * @return true si la partida se finalizó correctamente, false si no se encontró la partida activa.
     */
    public boolean finalizarPartida(String correoUsuario, String nombreVideojuego) {
        Usuario usuario = buscarUsuario(correoUsuario);
        if (usuario == null) {
            return false;
        }
        return usuario.eliminarPartida(nombreVideojuego);
    }
}