package model.planes;

import model.Videojuego;

/**
 * * Clase abstracta que representa un plan de suscripción en la plataforma de videojuegos.
 * Define las características comunes de todos los planes y los métodos que deben implementar las clases concretas.
 */
public abstract class Plan {
    protected String nombre;
    protected int velocidadMaxima;
    protected int maxPartidasSimultaneas;

    /**
     * Constructor para inicializar un plan de suscripción.
     *
     * @param nombre                 El nombre del plan (e.g., "Basic", "Premium").
     * @param velocidadMaxima        La velocidad máxima de conexión permitida para este plan (en Mbps).
     * @param maxPartidasSimultaneas El número máximo de partidas simultáneas permitidas para este plan.
     */
    public Plan(String nombre, int velocidadMaxima, int maxPartidasSimultaneas) {
        this.nombre = nombre;
        this.velocidadMaxima = velocidadMaxima;
        this.maxPartidasSimultaneas = maxPartidasSimultaneas;
    }

    /**
     * Métodos getters para acceder a las propiedades del plan.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener la velocidad máxima permitida por el plan.
     *
     * @return La velocidad máxima en Mbps.
     */
    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    /**
     * Método para obtener el número máximo de partidas simultáneas permitidas por el plan.
     *
     * @return El número máximo de partidas simultáneas.
     */
    public int getMaxPartidasSimultaneas() {
        return maxPartidasSimultaneas;
    }

    /**
     * Método abstracto que debe ser implementado por las clases concretas para definir el tipo de catálogo de videojuegos al que tienen acceso.
     *
     * @return El tipo de catálogo (e.g., "Completo", "Reducido").
     */
    public abstract String getTipoCatalogo();

    /**
     * Método abstracto que debe ser implementado por las clases concretas para determinar si el plan permite acceder a un videojuego específico.
     *
     * @param videojuego El videojuego al que se desea acceder.
     * @return true si el plan permite acceder al videojuego, false en caso contrario.
     */
    public abstract boolean puedeAcceder(Videojuego videojuego);
}