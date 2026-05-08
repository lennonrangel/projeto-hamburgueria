package br.com.hamburgueria.cardapio;

public abstract class IngredienteDecorator implements ItemCardapio {

    private final ItemCardapio itemCardapio;

    protected IngredienteDecorator(ItemCardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
    }

    protected ItemCardapio getItemCardapio() {
        return itemCardapio;
    }
}
