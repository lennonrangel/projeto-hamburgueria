package hamburgueria.linhaproduto.combo;

import hamburgueria.linhaproduto.GourmetFactory;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.hamburguer.Hamburguer;

public class ComboGourmetFactory extends ComboFactory {
    @Override
    public MenuItem criarCombo() {
        ComboGourmet combo = new ComboGourmet();
        MenuItem principal = GourmetFactory.getInstancia().criarHamburguerPrincipal();

        combo.adicionarItem(principal);

        combo.adicionarItem(new Hamburguer("Milkshake de Morango", 18.0));

        combo.adicionarItem(new Hamburguer("Batata Rústica", 15.0));

        return combo;
    }
}

