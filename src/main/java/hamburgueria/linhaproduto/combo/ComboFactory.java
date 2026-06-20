package hamburgueria.linhaproduto.combo;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.cardapio.RefeicaoCompleta;

public abstract class ComboFactory {

    public abstract MenuItem criarCombo();

    public RefeicaoCompleta montarCombo() {
        MenuItem combo = criarCombo();
        return (RefeicaoCompleta) combo;
    }
}

