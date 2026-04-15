package model;

/**
 * Clase que representa una partida de un videojuego para un usuario específico.
 */
public class Partida {
    private Usuario usuario;
    private Videojuego videojuego;
    private boolean activa;

    /**
     * Constructor para crear una nueva partida.
     * @param usuario El usuario que está jugando la partida.
     * @param videojuego El videojuego que se está jugando.
     */
    public Partida(Usuario usuario, Videojuego videojuego) {
        this.usuario = usuario;
        this.videojuego = videojuego;
        this.activa = true;
    }

    /**
     * Getters para acceder a los atributos de la partida.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    /**
     * Método para verificar si la partida está activa.
     * @return true si la partida está activa, false si ha finalizado.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Método para finalizar la partida, estableciendo el estado a inactiva.
     */
    public void finalizar() {
        this.activa = false;
    }
}