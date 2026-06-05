package padroesestruturais.adapter;

import padroesestruturais.composite.ItemCardapio;

public class IngredientePesadoAdapter implements ItemCardapio {

    private final IngredientePesadoExterno ingredienteExterno;

    public IngredientePesadoAdapter(IngredientePesadoExterno ingredienteExterno) {
        this.ingredienteExterno = ingredienteExterno;
    }

    @Override
    public String getDescricao() {
        return ingredienteExterno.obterNome() + " (" + String.format("%.0f", ingredienteExterno.obterPesoEmGramas()) + "g)";
    }

    @Override
    public double getPreco() {
        return ingredienteExterno.obterPrecoPorQuilo() * ingredienteExterno.obterPesoEmGramas() / 1000.0;
    }
}
