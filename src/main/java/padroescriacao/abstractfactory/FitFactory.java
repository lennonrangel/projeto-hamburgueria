package padroescriacao.abstractfactory;

import padroesestruturais.composite.ItemCardapio;
import padroescriacao.builder.ReceitaHamburguer;
import padroescriacao.builder.ChefeCozinha;
import padroesestruturais.bridge.AoPonto;
import padroesestruturais.bridge.BemPassado;
import padroesestruturais.bridge.ProteinaFrango;

public class FitFactory implements HamburguerFactory {

    private static FitFactory instancia;

    private FitFactory() {
    }

    public static synchronized FitFactory getInstancia() {
        if (instancia == null) {
            instancia = new FitFactory();
        }
        return instancia;
    }

    @Override
    public ItemCardapio criarHamburguerPrincipal() {
        System.out.println("Criando Hamburguer Principal da linha Fit");
        return new ChefeCozinha()
                .comNome("Fit da Casa")
                .comPao("integral")
                .comQueijo("minas")
                .comProteina(new ProteinaFrango(new BemPassado()))
                .comPrecoBase(12.0)
                .montar();
    }

    @Override
    public ItemCardapio criarHamburguerEspecial() {
        System.out.println();
        System.out.println("Criando Hamburguer Especial da linha Fit");
        return new ReceitaHamburguer().montarHamburguer("Fit Frango", "integral", new ProteinaFrango(new AoPonto()), 10.0);
    }

    @Override
    public String getNomeLinha() {
        return "Fit";
    }
}
