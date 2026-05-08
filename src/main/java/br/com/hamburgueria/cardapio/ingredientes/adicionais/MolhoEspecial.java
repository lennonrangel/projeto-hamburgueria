package br.com.hamburgueria.cardapio.ingredientes.adicionais;

import br.com.hamburgueria.cardapio.IngredienteDecorator;
import br.com.hamburgueria.cardapio.ItemCardapio;

public class MolhoEspecial extends IngredienteDecorator {

    public MolhoEspecial(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return getItemCardapio().getDescricao() + ", molho especial";
    }

    @Override
    public double getPreco() {
        return getItemCardapio().getPreco() + 2.0;
    }
}
