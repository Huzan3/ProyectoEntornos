package model.planes;

import model.Videojuego;

/**
 * Plan Basic: Permite acceso a videojuegos de categoría 'B' y tiene un costo mensual de 50 unidades monetarias.
 */
public class PlanBasic extends Plan {

    public PlanBasic() {
        super("Basic", 50, 1);
    }

    /**
     * Devuelve el tipo de catálogo al que tiene acceso este plan.
     *
     * @return "Reducido" indicando que el plan Basic tiene acceso a un catálogo reducido de videojuegos.
     */
    @Override
    public String getTipoCatalogo() {
        return "Reducido";
    }

    /**
     * Verifica si el plan Basic permite acceder a un videojuego específico.
     *
     * @param videojuego El videojuego que se desea verificar.
     * @return true si el videojuego pertenece a la categoría 'B', false en caso contrario.
     */
    @Override
    public boolean puedeAcceder(Videojuego videojuego) {
        return videojuego.getCategoria() == 'B';
    }
}