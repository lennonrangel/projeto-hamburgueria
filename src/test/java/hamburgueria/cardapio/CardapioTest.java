package hamburgueria.cardapio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hamburgueria.linhaproduto.ClassicoFactory;
import hamburgueria.linhaproduto.FitFactory;
import hamburgueria.hamburguer.Hamburguer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Iterator - Navegação do Cardápio")
class CardapioTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve percorrer itens do cardápio")
    void devePercorrerItensDoCardapio() {
        imprimirSeparador("Percorrendo Cardápio");
        Cardapio cardapio = new Cardapio();
        cardapio.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        cardapio.adicionarItem(FitFactory.getInstancia().criarHamburguerPrincipal());

        IteradorCardapio iterador = cardapio.createIterator();
        int totalItens = 0;
        double totalPreco = 0.0;

        while (iterador.hasMore()) {
            MenuItem item = iterador.getNext();
            totalItens++;
            totalPreco += item.getPreco();
        }

        assertEquals(2, totalItens);
        assertTrue(totalPreco > 0);
        assertFalse(iterador.hasMore());
    }

    @Test
    @DisplayName("Deve retornar nulo ao finalizar iteração")
    void deveRetornarNuloAoFinalizarIteracao() {
        imprimirSeparador("Fim da Iteração");
        Cardapio cardapio = new Cardapio();
        cardapio.adicionarItem(new Hamburguer("Burger Teste", 10.0));

        IteradorCardapio iterador = cardapio.createIterator();
        
        MenuItem item1 = iterador.getNext();
        assertNotNull(item1);

        MenuItem item2 = iterador.getNext();
        assertNull(item2);
    }

    @Test
    @DisplayName("Iterador de cardapio vazio deve retornar falso e nulo")
    void testIteradorCardapioVazio() {
        Cardapio cardapio = new Cardapio();
        IteradorCardapio iterador = cardapio.createIterator();
        assertFalse(iterador.hasMore());
        assertNull(iterador.getNext());
    }

    @Test
    @DisplayName("Lista de itens do cardapio deve ser imutavel")
    void testCardapioItensImodificaveis() {
        Cardapio cardapio = new Cardapio();
        assertThrows(UnsupportedOperationException.class, () -> {
            cardapio.getItens().add(new Hamburguer("Tentativa", 15.0));
        });
    }
}

