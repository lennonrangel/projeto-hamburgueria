package padroescriacao.factorymethod;

import padroesestruturais.composite.ItemCardapio;
import padroesestruturais.composite.RefeicaoCompleta;
import padroescriacao.abstractfactory.ClassicoFactory;
import padroescriacao.abstractfactory.Hamburguer;

public class ComboClassicoFactory extends ComboFactory {
    @Override
    public ItemCardapio criarCombo() {
        RefeicaoCompleta combo = new RefeicaoCompleta("Combo Clássico");
        ItemCardapio principal = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
        
        System.out.println("Criando Combo Clássico:");
        System.out.println("- " + principal.getDescricao());
        combo.adicionarItem(principal);
        
        System.out.println("- " + "Refrigerante");
        combo.adicionarItem(new Hamburguer("Refrigerante", 8.0));
        
        System.out.println("- " + "Batata Frita");
        combo.adicionarItem(new Hamburguer("Batata Frita", 10.0));
        
        return combo;
    }
}
