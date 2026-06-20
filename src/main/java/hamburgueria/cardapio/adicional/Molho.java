package hamburgueria.cardapio.adicional;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.estoque.IngredienteFactory;


public class Molho extends Complemento {

    public Molho(MenuItem menuItem) {
        super(menuItem, IngredienteFactory.getInstancia().getIngrediente("Molho Especial", "Ingrediente Extra", 1.5));
    }
}

