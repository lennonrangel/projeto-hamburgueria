package br.com.hamburgueria;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.fabricas.CardapioClassico;
import br.com.hamburgueria.cardapio.fabricas.CardapioFactory;
import br.com.hamburgueria.cardapio.fabricas.CardapioFit;
import br.com.hamburgueria.cardapio.fabricas.CardapioGourmet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HamburgueriaTest {

    @Test
    void deveUsarMesmaInstanciaDoCardapioClassico() {
        assertSame(CardapioClassico.getInstancia(), CardapioClassico.getInstancia());
    }

    @Test
    void deveCriarLanchesDeFamiliasDiferentes() {
        CardapioFactory classico = CardapioClassico.getInstancia();
        CardapioFactory gourmet = CardapioGourmet.getInstancia();
        CardapioFactory fit = CardapioFit.getInstancia();

        assertEquals("Clássicos", classico.getNomeLinha());
        assertEquals("Gourmet", gourmet.getNomeLinha());
        assertEquals("Fit", fit.getNomeLinha());
    }

    @Test
    void deveCriarLancheComProteinaEFormaDePreparoSeparadas() {
        ItemCardapio lanche = CardapioGourmet.getInstancia().criarLanchePrincipal();

        assertTrue(lanche.getDescricao().contains("picanha"));
        assertTrue(lanche.getDescricao().contains("mal passado"));
        assertTrue(lanche.getPreco() > 0);
    }
}
