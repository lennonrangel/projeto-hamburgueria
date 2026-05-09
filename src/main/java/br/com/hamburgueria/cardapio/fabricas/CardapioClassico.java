package br.com.hamburgueria.cardapio.fabricas;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.montagem.DiretorLanche;
import br.com.hamburgueria.cardapio.montagem.MontadorLanche;
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
        return new MontadorLanche()
                .comNome("Clássico da Casa")
                .comPao("australiano")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(8.0)
                .montar();
    }

    @Override
    public ItemCardapio criarLancheEspecial() {
        return new DiretorLanche().montarComboDaCasa("Duplo Clássico", "brioche", new ProteinaSmash(new BemPassado()), 11.0);
    }

    @Override
    public String getNomeLinha() {
        return "Clássicos";
    }
}
