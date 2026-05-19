package padroescriacao.abstractfactory;

import padroesestruturais.composite.ItemCardapio;
import padroescriacao.builder.ReceitaHamburguer;
import padroescriacao.builder.ChefeCozinha;
import padroesestruturais.bridge.BemPassado;
import padroesestruturais.bridge.ProteinaSmash;

public class ClassicoFactory implements HamburguerFactory {

    private static ClassicoFactory instancia;

    private ClassicoFactory() {
    }

    public static synchronized ClassicoFactory getInstancia() {
        if (instancia == null) {
            instancia = new ClassicoFactory();
        }
        return instancia;
    }

    @Override
    public ItemCardapio criarHamburguerPrincipal() {
        System.out.println("Criando Hamburguer Principal da linha Clássica");
        return new ChefeCozinha()
                .comNome("Clássico da Casa")
                .comPao("australiano")
                .comQueijo("cheddar")
                .comProteina(new ProteinaSmash(new BemPassado()))
                .comPrecoBase(10.0)
                .montar();
    }

    @Override
    public ItemCardapio criarHamburguerEspecial() {
        System.out.println("Criando Hamburguer Especial da linha Clássica");
        return new ReceitaHamburguer().montarHamburguer("Smash", "australiano", new ProteinaSmash(new BemPassado()), 12.0);
    }

    @Override
    public String getNomeLinha() {
        return "Clássicos";
    }
}
