package padroesestruturais.decorator;

import padroesestruturais.composite.ItemCardapio;

public abstract class Complemento implements ItemCardapio {

    private final ItemCardapio itemCardapio;

    protected Complemento(ItemCardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
    }

    protected ItemCardapio getItemCardapio() {
        return itemCardapio;
    }
}
