package br.com.hamburgueria.cardapio.ingredientes.adicionais;

import br.com.hamburgueria.cardapio.IngredienteDecorator;
import br.com.hamburgueria.cardapio.ItemCardapio;

public class QueijoExtra extends IngredienteDecorator {

    public QueijoExtra(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", queijo extra";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 3.0;
    }
}
