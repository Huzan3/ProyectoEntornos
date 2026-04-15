package model;

/**
 * Clase que representa un videojuego con sus atributos básicos.
 */
public class Videojuego {
    private String nombre;
    private String genero;
    private int velocidadMinima;
    private char categoria;
    /**
     * Constructor para crear un nuevo videojuego.
     *
     * @param nombre          El nombre del videojuego.
     * @param genero          El género del videojuego (e.g., acción, aventura).
     * @param velocidadMinima La velocidad mínima requerida para jugar el videojuego.
     * @param categoria       La categoría del videojuego (e.g., 'A' para todos los públicos, 'B' para mayores de 12 años).
     */
    public Videojuego(String nombre, String genero, int velocidadMinima, char categoria) {
        this.nombre = nombre;
        this.genero = genero;
        this.velocidadMinima = velocidadMinima;
        this.categoria = categoria;
    }
    /**
     * Getters para acceder a los atributos del videojuego.
     */
    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    /**
     * El método getVelocidadMinima devuelve la velocidad mínima requerida para jugar el videojuego.
     * Esta información es crucial para que los usuarios puedan asegurarse de que su dispositivo cumple con los requisitos necesarios para disfrutar del juego sin problemas.
     */
    public int getVelocidadMinima() {
        return velocidadMinima;
    }

    /**
     * El método getCategoria devuelve la categoría del videojuego, que indica el público objetivo para el juego.
     * Esto ayuda a los usuarios a determinar si el juego es adecuado para ellos o para sus hijos, basándose en la clasificación de edad y contenido.
     */
    public char getCategoria() {
        return categoria;
    }
}