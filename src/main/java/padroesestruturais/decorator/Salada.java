package padroesestruturais.decorator;

import padroesestruturais.composite.ItemCardapio;

public class Salada extends Complemento {

    public Salada(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", salada (alface e tomate)";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 0.0;
    }
}
