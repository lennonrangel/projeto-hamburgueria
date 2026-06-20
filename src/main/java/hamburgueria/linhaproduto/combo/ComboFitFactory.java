package hamburgueria.linhaproduto.combo;

import hamburgueria.linhaproduto.FitFactory;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.hamburguer.Hamburguer;

public class ComboFitFactory extends ComboFactory {
    @Override
    public MenuItem criarCombo() {
        ComboFit combo = new ComboFit();
        MenuItem principal = FitFactory.getInstancia().criarHamburguerPrincipal();

        combo.adicionarItem(principal);

        combo.adicionarItem(new Hamburguer("Suco Natural", 12.0));

        combo.adicionarItem(new Hamburguer("Batata Frita", 10.0));

        return combo;
    }
}

