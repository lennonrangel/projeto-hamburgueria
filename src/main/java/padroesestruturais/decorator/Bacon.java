package padroesestruturais.decorator;

import padroesestruturais.composite.ItemCardapio;

public class Bacon extends Complemento {

    public Bacon(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", bacon";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 4.0;
    }
}
