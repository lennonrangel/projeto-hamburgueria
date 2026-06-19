package hamburgueria.linhaproduto;


import hamburgueria.cardapio.MenuItem;
import hamburgueria.hamburguer.ReceitaHamburguer;
import hamburgueria.hamburguer.ChefeCozinha;
import hamburgueria.hamburguer.ponto.AoPonto;
import hamburgueria.hamburguer.ponto.BemPassado;
import hamburgueria.hamburguer.proteina.ProteinaFrango;

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
    public MenuItem criarHamburguerPrincipal() {
        return new ChefeCozinha()
                .comNome("Fit da Casa")
                .comPao("integral")
                .comQueijo("minas")
                .comProteina(new ProteinaFrango(new BemPassado()))
                .comPrecoBase(12.0)
                .montar();
    }

    @Override
    public MenuItem criarHamburguerEspecial() {
        return new ReceitaHamburguer().montarHamburguer("Fit Frango", "integral", new ProteinaFrango(new AoPonto()), 10.0);
    }

    @Override
    public String getNomeLinha() {
        return "Fit";
    }
}

