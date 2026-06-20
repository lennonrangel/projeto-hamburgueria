package hamburgueria.cardapio.adicional;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.estoque.IngredienteFactory;


public class Salada extends Complemento {

    public Salada(MenuItem menuItem) {
        super(menuItem, IngredienteFactory.getInstancia().getIngrediente("Salada (alface e tomate)", "Vegetal", 0.0));
    }
}

