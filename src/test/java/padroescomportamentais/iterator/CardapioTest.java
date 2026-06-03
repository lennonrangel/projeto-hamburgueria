package padroescomportamentais.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroescriacao.abstractfactory.ClassicoFactory;
import padroescriacao.abstractfactory.FitFactory;
import padroescriacao.abstractfactory.Hamburguer;
import padroesestruturais.composite.ItemCardapio;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Iterator - Navegação do Cardápio")
class CardapioTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [ITERATOR] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve percorrer itens do cardápio")
    void devePercorrerItensDoCardapio() {
        imprimirSeparador("Percorrendo Cardápio");

        Cardapio cardapio = new Cardapio();
        cardapio.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        cardapio.adicionarItem(FitFactory.getInstancia().criarHamburguerPrincipal());

        IteradorCardapio iterador = cardapio.criarIterador();
        int totalItens = 0;
        double totalPreco = 0.0;

        while (iterador.temProximo()) {
            ItemCardapio item = iterador.proximo();
            System.out.println(item.getDescricao() + " - R$ " + String.format("%.2f", item.getPreco()));
            totalItens++;
            totalPreco += item.getPreco();
        }

        assertEquals(2, totalItens);
        assertTrue(totalPreco > 0);
        assertFalse(iterador.temProximo());
    }

    @Test
    @DisplayName("Deve retornar nulo ao finalizar iteração")
    void deveRetornarNuloAoFinalizarIteracao() {
        imprimirSeparador("Fim da Iteração");

        Cardapio cardapio = new Cardapio();
        cardapio.adicionarItem(new Hamburguer("Burger Teste", 10.0));

        IteradorCardapio iterador = cardapio.criarIterador();
        
        ItemCardapio item1 = iterador.proximo();
        System.out.println("Primeiro item recuperado: " + (item1 != null ? item1.getDescricao() : "null"));
        assertNotNull(item1);

        ItemCardapio item2 = iterador.proximo();
        System.out.println("Tentativa de recuperar próximo item (esperado null): " + item2);
        assertNull(item2);
    }
}
