package hamburgueria.pedido.comando;

import hamburgueria.pedido.Pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hamburgueria.hamburguer.Hamburguer;
import hamburgueria.cardapio.MenuItem;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Command - Acoes de Pedido")
class ComandoPedidoTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve adicionar item usando comando")
    void deveAdicionarItemUsandoComando() {
        imprimirSeparador("Adicionar Item");
        Pedido pedido = new Pedido();
        MenuItem item = new Hamburguer("Burger Command", 24.0);
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();

        gerenciador.executar(new AdicionarItemCommand(pedido, item));


        assertEquals(1, pedido.getItens().size());
        assertEquals(24.0, pedido.calcularTotal(), 0.01);
        assertEquals(1, gerenciador.getQuantidadeComandosExecutados());
    }

    @Test
    @DisplayName("Deve remover item usando comando")
    void deveRemoverItemUsandoComando() {
        imprimirSeparador("Remover Item");
        Pedido pedido = new Pedido();
        MenuItem item = new Hamburguer("Burger Remover", 26.0);
        pedido.adicionarItem(item);

        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();
        gerenciador.executar(new RemoverItemCommand(pedido, item));


        assertTrue(pedido.getItens().isEmpty());
        assertEquals(0.0, pedido.calcularTotal(), 0.01);
        assertEquals(1, gerenciador.getQuantidadeComandosExecutados());
    }

    @Test
    @DisplayName("Deve desfazer ultimo comando executado")
    void deveDesfazerUltimoComandoExecutado() {
        imprimirSeparador("Desfazer Comando");
        Pedido pedido = new Pedido();
        MenuItem item = new Hamburguer("Burger Undo", 28.0);
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();

        gerenciador.executar(new AdicionarItemCommand(pedido, item));
        gerenciador.desfazerUltimo();


        assertTrue(pedido.getItens().isEmpty());
        assertEquals(0.0, pedido.calcularTotal(), 0.01);
    }

    @Test
    @DisplayName("Deve desfazer remocao de item")
    void deveDesfazerRemocaoDeItem() {
        imprimirSeparador("Desfazer Remocao");
        Pedido pedido = new Pedido();
        MenuItem item = new Hamburguer("Burger Restaurado", 31.0);
        pedido.adicionarItem(item);

        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();
        gerenciador.executar(new RemoverItemCommand(pedido, item));
        gerenciador.desfazerUltimo();


        assertEquals(1, pedido.getItens().size());
        assertEquals(31.0, pedido.calcularTotal(), 0.01);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Desfazer sem comandos no gerenciador não deve lançar exceção")
    void testGerenciadorComandosPedidoDesfazerVazio() {
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();
        assertDoesNotThrow(() -> gerenciador.desfazerUltimo());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Gerenciador de comandos deve contar comandos executados corretamente")
    void testGerenciadorComandosPedidoQuantidade() {
        Pedido pedido = new Pedido();
        MenuItem item = new Hamburguer("Burger", 15.0);
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();
        assertEquals(0, gerenciador.getQuantidadeComandosExecutados());
        gerenciador.executar(new AdicionarItemCommand(pedido, item));
        assertEquals(1, gerenciador.getQuantidadeComandosExecutados());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Deve permitir desfazer multiplos comandos sequenciais")
    void testDesfazerMultiplosComandos() {
        Pedido pedido = new Pedido();
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();

        gerenciador.executar(new AdicionarItemCommand(pedido, new Hamburguer("Burger 1", 10.0)));
        gerenciador.executar(new AdicionarItemCommand(pedido, new Hamburguer("Burger 2", 20.0)));
        gerenciador.executar(new AdicionarItemCommand(pedido, new Hamburguer("Burger 3", 30.0)));

        assertEquals(3, pedido.getItens().size());

        gerenciador.desfazerUltimo();
        assertEquals(2, pedido.getItens().size());
        assertEquals(30.0, pedido.calcularTotal(), 0.01);

        gerenciador.desfazerUltimo();
        assertEquals(1, pedido.getItens().size());
        assertEquals(10.0, pedido.calcularTotal(), 0.01);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Deve manter consistência ao misturar comandos de adicionar, remover e desfazer")
    void testAdicionarERemoverNaMesmaSessao() {
        Pedido pedido = new Pedido();
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();
        MenuItem item = new Hamburguer("Burger Mix", 25.0);

        gerenciador.executar(new AdicionarItemCommand(pedido, item));
        gerenciador.executar(new RemoverItemCommand(pedido, item));
        assertTrue(pedido.getItens().isEmpty());

        gerenciador.desfazerUltimo(); // Desfaz a remoção, deve ter 1 item
        assertEquals(1, pedido.getItens().size());
        assertEquals(25.0, pedido.calcularTotal(), 0.01);

        gerenciador.desfazerUltimo(); // Desfaz a adição, deve ficar vazio
        assertTrue(pedido.getItens().isEmpty());
    }
}

