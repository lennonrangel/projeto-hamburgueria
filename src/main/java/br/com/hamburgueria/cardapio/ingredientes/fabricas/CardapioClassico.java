package br.com.hamburgueria.cardapio.fabricas;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.Lanche;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.AoPonto;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.BemPassado;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.ProteinaSmash;

public class CardapioClassico implements CardapioFactory {

    private static CardapioClassico instancia;

    private CardapioClassico() {
    }

    public static CardapioClassico getInstancia() {
        if (instancia == null) {
            instancia = new CardapioClassico();
        }
        return instancia;
    }

    @Override
    public ItemCardapio criarLanchePrincipal() {
        return new Lanche("Clássico da Casa", "australiano", new ProteinaSmash(new AoPonto()), 8.0);
    }

    @Override
    public ItemCardapio criarLancheEspecial() {
        return new Lanche("Duplo Clássico", "brioche", new ProteinaSmash(new BemPassado()), 11.0);
    }

    @Override
    public String getNomeLinha() {
        return "Clássicos";
    }
}
