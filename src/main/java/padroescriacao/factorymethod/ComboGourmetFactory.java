package padroescriacao.factorymethod;

import padroesestruturais.composite.ItemCardapio;
import padroesestruturais.composite.RefeicaoCompleta;
import padroescriacao.abstractfactory.GourmetFactory;
import padroescriacao.abstractfactory.Hamburguer;

public class ComboGourmetFactory extends ComboFactory {
    @Override
    public ItemCardapio criarCombo() {
        RefeicaoCompleta combo = new RefeicaoCompleta("Combo Gourmet");
        ItemCardapio principal = GourmetFactory.getInstancia().criarHamburguerPrincipal();

        System.out.println("Criando Combo Gourmet:");
        System.out.println("- " + principal.getDescricao());
        combo.adicionarItem(principal);

        System.out.println("- " + "Milkshake de Morango");
        combo.adicionarItem(new Hamburguer("Milkshake de Morango", 18.0));

        System.out.println("- " + "Batata Rústica");
        combo.adicionarItem(new Hamburguer("Batata Rústica", 15.0));

        return combo;
    }
}
