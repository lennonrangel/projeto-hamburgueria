package hamburgueria.linhaproduto.combo;

import hamburgueria.linhaproduto.ClassicoFactory;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.hamburguer.Hamburguer;

public class ComboClassicoFactory extends ComboFactory {
    @Override
    public MenuItem criarCombo() {
        ComboClassico combo = new ComboClassico();
        MenuItem principal = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
        
        combo.adicionarItem(principal);
        
        combo.adicionarItem(new Hamburguer("Refrigerante", 8.0));
        
        combo.adicionarItem(new Hamburguer("Batata Frita", 10.0));
        
        return combo;
    }
}

