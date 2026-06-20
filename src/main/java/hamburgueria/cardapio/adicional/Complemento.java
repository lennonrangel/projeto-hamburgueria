package hamburgueria.cardapio.adicional;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.estoque.IngredienteCompartilhado;


public abstract class Complemento implements MenuItem {

    private final MenuItem menuItem;
    private final IngredienteCompartilhado ingrediente;

    protected Complemento(MenuItem menuItem, IngredienteCompartilhado ingrediente) {
        this.menuItem = menuItem;
        this.ingrediente = ingrediente;
    }

    @Override
    public String getDescricao() {
        return menuItem.getDescricao() + ", " + ingrediente.getNome().toLowerCase();
    }

    @Override
    public double getPreco() {
        return menuItem.getPreco() + ingrediente.getPrecoUnitario();
    }

    protected MenuItem getMenuItem() {
        return menuItem;
    }

    protected IngredienteCompartilhado getIngrediente() {
        return ingrediente;
    }
}

