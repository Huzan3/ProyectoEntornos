package model;

import model.planes.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario de la plataforma de videojuegos.
 * Contiene información personal, el plan contratado y las partidas activas.
 */
public class Usuario {
    private String nombreCompleto;
    private String correo;
    private Plan plan;
    private List<Partida> partidasActivas;
    /**
     * Constructor para crear un nuevo usuario.
     *
     * @param nombreCompleto El nombre completo del usuario.
     * @param correo        El correo electrónico del usuario.
     * @param plan          El plan contratado por el usuario.
     */
    public Usuario(String nombreCompleto, String correo, Plan plan) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.plan = plan;
        this.partidasActivas = new ArrayList<>();
    }
    /**
     * Getters y setters para los atributos de la clase.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<Partida> getPartidasActivas() {
        return partidasActivas;
    }
    /**
     * Método para obtener el número de partidas activas del usuario.
     *
     * @return El número de partidas activas.
     */
    public int getNumPartidasActivas() {
        int contador = 0;
        for (Partida partida : partidasActivas) {
            if (partida.isActiva()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Método para agregar una nueva partida activa al usuario.
     *
     * @param partida La partida que se desea agregar.
     */
    public void agregarPartida(Partida partida) {
        partidasActivas.add(partida);
    }

    /**
     * Método para eliminar una partida activa del usuario.
     *
     * @param nombreVideojuego El nombre del videojuego de la partida que se desea eliminar.
     * @return true si la partida fue eliminada exitosamente, false si no se encontró la partida.
     */
    public boolean eliminarPartida(String nombreVideojuego) {
        for (int i = 0; i < partidasActivas.size(); i++) {
            Partida partida = partidasActivas.get(i);
            if (partida.isActiva() && partida.getVideojuego().getNombre().equalsIgnoreCase(nombreVideojuego)) {
                partida.finalizar();
                partidasActivas.remove(i);
                return true;
            }
        }
        return false;
    }
}