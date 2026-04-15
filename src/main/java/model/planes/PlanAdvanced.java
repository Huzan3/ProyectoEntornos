package model.planes;

import model.Videojuego;

/**
 * Clase que representa el plan "Advanced" de la plataforma de videojuegos.
 * Este plan tiene un costo mensual de 150 y permite acceder a videojuegos de categoría 'B' y 'A'.
 */
public class PlanAdvanced extends Plan {

    /**
     * Constructor para crear una instancia del plan "Advanced".
     * Establece el nombre del plan, el costo mensual y el número máximo de partidas activas.
     */
    public PlanAdvanced() {
        super("Advanced", 150, 2);
    }

    /**
     * Método que devuelve el tipo de catálogo al que tiene acceso este plan.
     *
     * @return El tipo de catálogo, en este caso "Ampliado".
     */
    @Override
    public String getTipoCatalogo() {
        return "Ampliado";
    }

    /**
     * Método que determina si el usuario con este plan puede acceder a un videojuego específico.
     * Permite acceder a videojuegos de categoría 'B' y 'A'.
     *
     * @param videojuego El videojuego al que se desea acceder.
     * @return true si el usuario puede acceder al videojuego, false en caso contrario.
     */
    @Override
    public boolean puedeAcceder(Videojuego videojuego) {
        return videojuego.getCategoria() == 'B' || videojuego.getCategoria() == 'A';
    }
}
