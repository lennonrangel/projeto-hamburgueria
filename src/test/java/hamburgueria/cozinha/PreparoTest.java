package hamburgueria.cozinha;

import hamburgueria.pedido.Pedido;
import hamburgueria.linhaproduto.ClassicoFactory;
import hamburgueria.linhaproduto.GourmetFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Teste do Padrão Template Method - Preparo de Pedidos")
class PreparoTest {

    private void imprimirSeparador(String titulo) {
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        imprimirSeparador(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Deve usar template method clássico para preparar")
    void deveUsarTemplateMethodClassico() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        pedido.avancar();
        new ProcessoPreparoClassico().preparar(pedido);
        assertEquals("Pronto", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve usar template method gourmet para preparar")
    void deveUsarTemplateMethodGourmet() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(GourmetFactory.getInstancia().criarHamburguerPrincipal());
        pedido.avancar();
        new ProcessoPreparoGourmet().preparar(pedido);
        assertEquals("Pronto", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve usar template method fit para preparar")
    void deveUsarTemplateMethodFit() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        pedido.avancar();
        new ProcessoPreparoFit().preparar(pedido);
        assertEquals("Pronto", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Preparo não deve avançar pedido se não estiver em preparo")
    void testPreparoNaoAvancaPedidoSeNaoEstiverEmPreparo() {
        Pedido pedido = new Pedido();
        new ProcessoPreparoClassico().preparar(pedido);
        assertEquals("Recebido", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Preparo de pedido nulo deve lançar exceção")
    void testPreparoPedidoNulo() {
        ProcessoPreparo preparo = new ProcessoPreparoClassico();
        assertThrows(NullPointerException.class, () -> preparo.preparar(null));
    }
}
