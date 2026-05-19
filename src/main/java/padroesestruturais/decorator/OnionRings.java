package padroesestruturais.decorator;

import padroesestruturais.composite.ItemCardapio;

public class OnionRings extends Complemento {

    public OnionRings(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", onion rings";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 3.0;
    }
}
