package padroesestruturais.decorator;

import padroesestruturais.composite.ItemCardapio;

public class Picles extends Complemento {

    public Picles(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", picles";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 1.0;
    }
}
