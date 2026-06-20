package hamburgueria.cardapio.adicional;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.estoque.IngredienteFactory;


public class Picles extends Complemento {

    public Picles(MenuItem menuItem) {
        super(menuItem, IngredienteFactory.getInstancia().getIngrediente("Picles", "Ingrediente Extra", 1.0));
    }
}

