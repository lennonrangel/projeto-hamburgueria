package hamburgueria.linhaproduto;


import hamburgueria.cardapio.MenuItem;
import hamburgueria.hamburguer.ChefeCozinha;
import hamburgueria.hamburguer.ReceitaHamburguer;
import hamburgueria.hamburguer.ponto.AoPonto;
import hamburgueria.hamburguer.ponto.MalPassado;
import hamburgueria.hamburguer.proteina.ProteinaPicanha;

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
    public MenuItem criarHamburguerPrincipal() {
        return new ChefeCozinha()
                .comNome("Gourmet da Casa")
                .comPao("brioche")
                .comQueijo("gruyère")
                .comProteina(new ProteinaPicanha(new AoPonto()))
                .comPrecoBase(16.0)
                .montar();
    }

    @Override
    public MenuItem criarHamburguerEspecial() {
        return new ReceitaHamburguer().montarHamburguer("Gourmet Picanha", "brioche", new ProteinaPicanha(new MalPassado()), 16.0);
    }

    @Override
    public String getNomeLinha() {
        return "Gourmet";
    }
}

