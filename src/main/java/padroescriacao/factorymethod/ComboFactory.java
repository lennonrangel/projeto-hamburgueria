package padroescriacao.factorymethod;

import padroesestruturais.composite.ItemCardapio;
import padroesestruturais.composite.RefeicaoCompleta;

public abstract class ComboFactory {

    public abstract ItemCardapio criarCombo();

    public RefeicaoCompleta montarCombo() {
        ItemCardapio combo = criarCombo();
        return (RefeicaoCompleta) combo;
    }
}
