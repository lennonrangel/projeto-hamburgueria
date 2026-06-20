package hamburgueria.cardapio.adicional;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.estoque.IngredienteFactory;


public class Bacon extends Complemento {

    public Bacon(MenuItem menuItem) {
        super(menuItem, IngredienteFactory.getInstancia().getIngrediente("Bacon", "Ingrediente Extra", 4.0));
    }
}

