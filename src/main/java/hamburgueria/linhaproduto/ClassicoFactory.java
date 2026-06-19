package hamburgueria.linhaproduto;


import hamburgueria.cardapio.MenuItem;
import hamburgueria.hamburguer.ReceitaHamburguer;
import hamburgueria.hamburguer.ChefeCozinha;
import hamburgueria.hamburguer.ponto.BemPassado;
import hamburgueria.hamburguer.proteina.ProteinaSmash;

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
    public MenuItem criarHamburguerPrincipal() {
        return new ChefeCozinha()
                .comNome("Clássico da Casa")
                .comPao("australiano")
                .comQueijo("cheddar")
                .comProteina(new ProteinaSmash(new BemPassado()))
                .comPrecoBase(10.0)
                .montar();
    }

    @Override
    public MenuItem criarHamburguerEspecial() {
        return new ReceitaHamburguer().montarHamburguer("Smash", "australiano", new ProteinaSmash(new BemPassado()), 12.0);
    }

    @Override
    public String getNomeLinha() {
        return "Clássicos";
    }
}

