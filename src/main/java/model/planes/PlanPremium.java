package model.planes;

import model.Videojuego;
/**
 * Plan Premium: Permite acceso a videojuegos de categorías 'B', 'A' y 'P' y tiene un costo mensual de 500 unidades monetarias.
 */
public class PlanPremium extends Plan {

    /**
     * Constructor del Plan Premium que inicializa el nombre, costo mensual y nivel de acceso.
     */
    public PlanPremium() {
        super("Premium", 500, 4);
    }

    /**
     * Devuelve el tipo de catálogo al que tiene acceso este plan.
     *
     * @return "Completo" indicando que el plan Premium tiene acceso a un catálogo completo de videojuegos.
     */
    @Override
    public String getTipoCatalogo() {
        return "Completo";
    }

    /**
     * Verifica si el plan Premium permite acceder a un videojuego específico.
     *
     * @param videojuego El videojuego que se desea verificar.
     * @return true si el videojuego pertenece a las categorías 'B', 'A' o 'P', false en caso contrario.
     */
    @Override
    public boolean puedeAcceder(Videojuego videojuego) {
        return videojuego.getCategoria() == 'B'
                || videojuego.getCategoria() == 'A'
                || videojuego.getCategoria() == 'P';
    }
}