package hamburgueria.estoque;

import hamburgueria.cardapio.MenuItem;

public class IngredienteGranelAdapter implements MenuItem {

    private final IngredienteGranel ingredienteGranel;

    public IngredienteGranelAdapter(IngredienteGranel ingredienteGranel) {
        this.ingredienteGranel = ingredienteGranel;
    }

    @Override
    public String getDescricao() {
        return ingredienteGranel.obterNome() + " (" + String.format("%.0f", ingredienteGranel.obterPesoEmGramas()) + "g)";
    }

    @Override
    public double getPreco() {
        return ingredienteGranel.obterPrecoPorQuilo() * ingredienteGranel.obterPesoEmGramas() / 1000.0;
    }
}

