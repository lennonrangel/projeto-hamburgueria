package br.com.hamburgueria.cardapio.fabricas;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.montagem.DiretorLanche;
import br.com.hamburgueria.cardapio.montagem.MontadorLanche;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.AoPonto;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.MalPassado;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.ProteinaPicanha;

public class CardapioGourmet implements CardapioFactory {

    private static CardapioGourmet instancia;

    private CardapioGourmet() {
    }

    public static CardapioGourmet getInstancia() {
        if (instancia == null) {
            instancia = new CardapioGourmet();
        }
        return instancia;
    }

    @Override
    public ItemCardapio criarLanchePrincipal() {
        return new MontadorLanche()
                .comNome("Gourmet Picanha")
                .comPao("brioche")
                .comProteina(new ProteinaPicanha(new MalPassado()))
                .comPrecoBase(13.0)
                .montar();
    }

    @Override
    public ItemCardapio criarLancheEspecial() {
        return new DiretorLanche().montarComboDaCasa("Gourmet Especial", "parmesão", new ProteinaPicanha(new AoPonto()), 16.0);
    }

    @Override
    public String getNomeLinha() {
        return "Gourmet";
    }
}
