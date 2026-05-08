package br.com.hamburgueria.cardapio.ingredientes.adicionais;

import br.com.hamburgueria.cardapio.IngredienteDecorator;
import br.com.hamburgueria.cardapio.ItemCardapio;

public class BaconExtra extends IngredienteDecorator {

    public BaconExtra(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", bacon extra";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 4.5;
    }
}
