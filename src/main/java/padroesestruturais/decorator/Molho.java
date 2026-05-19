package padroesestruturais.decorator;

import padroesestruturais.composite.ItemCardapio;

public class Molho extends Complemento {

    public Molho(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", molho especial";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 1.5;
    }
}
