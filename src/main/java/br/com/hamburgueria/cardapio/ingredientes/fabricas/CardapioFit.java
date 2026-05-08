package br.com.hamburgueria.cardapio.fabricas;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.Lanche;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.AoPonto;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.BemPassado;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.ProteinaFrango;

public class CardapioFit implements CardapioFactory {

    private static CardapioFit instancia;

    private CardapioFit() {
    }

    public static CardapioFit getInstancia() {
        if (instancia == null) {
            instancia = new CardapioFit();
        }
        return instancia;
    }

    @Override
    public ItemCardapio criarLanchePrincipal() {
        return new Lanche("Fit Frango", "integral", new ProteinaFrango(new BemPassado()), 7.0);
    }

    @Override
    public ItemCardapio criarLancheEspecial() {
        return new Lanche("Fit Leve", "australiano", new ProteinaFrango(new AoPonto()), 6.5);
    }

    @Override
    public String getNomeLinha() {
        return "Fit";
    }
}
