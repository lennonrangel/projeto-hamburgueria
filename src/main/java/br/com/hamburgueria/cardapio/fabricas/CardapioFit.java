package br.com.hamburgueria.cardapio.fabricas;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.montagem.DiretorLanche;
import br.com.hamburgueria.cardapio.montagem.MontadorLanche;
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
        return new MontadorLanche()
                .comNome("Fit Frango")
                .comPao("integral")
                .comProteina(new ProteinaFrango(new BemPassado()))
                .comPrecoBase(7.0)
                .montar();
    }

    @Override
    public ItemCardapio criarLancheEspecial() {
        return new DiretorLanche().montarComboDaCasa("Fit Leve", "australiano", new ProteinaFrango(new AoPonto()), 6.5);
    }

    @Override
    public String getNomeLinha() {
        return "Fit";
    }
}
