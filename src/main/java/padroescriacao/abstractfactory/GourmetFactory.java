package padroescriacao.abstractfactory;

import padroesestruturais.composite.ItemCardapio;
import padroescriacao.builder.ChefeCozinha;
import padroescriacao.builder.ReceitaHamburguer;
import padroesestruturais.bridge.AoPonto;
import padroesestruturais.bridge.MalPassado;
import padroesestruturais.bridge.ProteinaPicanha;

public class GourmetFactory implements HamburguerFactory {

    private static GourmetFactory instancia;

    private GourmetFactory() {
    }

    public static synchronized GourmetFactory getInstancia() {
        if (instancia == null) {
            instancia = new GourmetFactory();
        }
        return instancia;
    }

    @Override
    public ItemCardapio criarHamburguerPrincipal() {
        System.out.println("Criando Hamburguer Principal da linha Gourmet");
        return new ChefeCozinha()
                .comNome("Gourmet da Casa")
                .comPao("brioche")
                .comQueijo("gruyère")
                .comProteina(new ProteinaPicanha(new AoPonto()))
                .comPrecoBase(16.0)
                .montar();
    }

    @Override
    public ItemCardapio criarHamburguerEspecial() {
        System.out.println();
        System.out.println("Criando Hamburguer Especial da linha Gourmet");
        return new ReceitaHamburguer().montarHamburguer("Gourmet Picanha", "brioche", new ProteinaPicanha(new MalPassado()), 16.0);
    }

    @Override
    public String getNomeLinha() {
        return "Gourmet";
    }
}
