package hamburgueria.cardapio.adicional;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.estoque.IngredienteFactory;


public class OnionRings extends Complemento {

    public OnionRings(MenuItem menuItem) {
        super(menuItem, IngredienteFactory.getInstancia().getIngrediente("Onion Rings", "Ingrediente Extra", 3.0));
    }
}

