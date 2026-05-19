package padroescriacao.factorymethod;

import padroesestruturais.composite.ItemCardapio;
import padroesestruturais.composite.RefeicaoCompleta;
import padroescriacao.abstractfactory.FitFactory;
import padroescriacao.abstractfactory.Hamburguer;

public class ComboFitFactory extends ComboFactory {
    @Override
    public ItemCardapio criarCombo() {
        RefeicaoCompleta combo = new RefeicaoCompleta("Combo Fit");
        ItemCardapio principal = FitFactory.getInstancia().criarHamburguerPrincipal();

        System.out.println("Criando Combo Fit:");
        System.out.println("- " + principal.getDescricao());
        combo.adicionarItem(principal);

        System.out.println("- " + "Suco Natural");
        combo.adicionarItem(new Hamburguer("Suco Natural", 12.0));

        System.out.println("- " + "Batata Frita");
        combo.adicionarItem(new Hamburguer("Batata Frita", 10.0));

        return combo;
    }
}
