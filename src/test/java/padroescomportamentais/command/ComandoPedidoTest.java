package padroescomportamentais.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroescomportamentais.state.Pedido;
import padroescriacao.abstractfactory.Hamburguer;
import padroesestruturais.composite.ItemCardapio;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Command - Acoes de Pedido")
class ComandoPedidoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [COMMAND] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve adicionar item usando comando")
    void deveAdicionarItemUsandoComando() {
        imprimirSeparador("Adicionar Item");
        System.out.println();
        Pedido pedido = new Pedido();
        ItemCardapio item = new Hamburguer("Burger Command", 24.0);
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();

        gerenciador.executar(new AdicionarItemCommand(pedido, item));

        System.out.println("Comando executado: adicionar item");
        System.out.println("Item: " + item.getDescricao());
        System.out.println("Total do pedido: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Comandos no historico: " + gerenciador.getQuantidadeComandosExecutados());

        assertEquals(1, pedido.getItens().size());
        assertEquals(24.0, pedido.calcularTotal(), 0.01);
        assertEquals(1, gerenciador.getQuantidadeComandosExecutados());
    }

    @Test
    @DisplayName("Deve remover item usando comando")
    void deveRemoverItemUsandoComando() {
        imprimirSeparador("Remover Item");
        System.out.println();
        Pedido pedido = new Pedido();
        ItemCardapio item = new Hamburguer("Burger Remover", 26.0);
        pedido.adicionarItem(item);

        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();
        gerenciador.executar(new RemoverItemCommand(pedido, item));

        System.out.println("Comando executado: remover item");
        System.out.println("Item removido: " + item.getDescricao());
        System.out.println("Total do pedido apos remocao: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Comandos no historico: " + gerenciador.getQuantidadeComandosExecutados());

        assertTrue(pedido.getItens().isEmpty());
        assertEquals(0.0, pedido.calcularTotal(), 0.01);
        assertEquals(1, gerenciador.getQuantidadeComandosExecutados());
    }

    @Test
    @DisplayName("Deve desfazer ultimo comando executado")
    void deveDesfazerUltimoComandoExecutado() {
        imprimirSeparador("Desfazer Comando");
        System.out.println();
        Pedido pedido = new Pedido();
        ItemCardapio item = new Hamburguer("Burger Undo", 28.0);
        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();

        gerenciador.executar(new AdicionarItemCommand(pedido, item));
        gerenciador.desfazerUltimo();

        System.out.println("Comando executado: adicionar item");
        System.out.println("Operacao desfeita: ultima adicao");
        System.out.println("Total do pedido apos desfazer: R$ " + String.format("%.2f", pedido.calcularTotal()));

        assertTrue(pedido.getItens().isEmpty());
        assertEquals(0.0, pedido.calcularTotal(), 0.01);
    }

    @Test
    @DisplayName("Deve desfazer remocao de item")
    void deveDesfazerRemocaoDeItem() {
        imprimirSeparador("Desfazer Remocao");
        System.out.println();
        Pedido pedido = new Pedido();
        ItemCardapio item = new Hamburguer("Burger Restaurado", 31.0);
        pedido.adicionarItem(item);

        GerenciadorComandosPedido gerenciador = new GerenciadorComandosPedido();
        gerenciador.executar(new RemoverItemCommand(pedido, item));
        gerenciador.desfazerUltimo();

        System.out.println("Comando executado: remover item");
        System.out.println("Operacao desfeita: item voltou ao pedido");
        System.out.println("Total do pedido restaurado: R$ " + String.format("%.2f", pedido.calcularTotal()));

        assertEquals(1, pedido.getItens().size());
        assertEquals(31.0, pedido.calcularTotal(), 0.01);
    }
}
