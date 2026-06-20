package hamburgueria.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Teste do Padrão State - Estados do Pedido")
class PedidoTest {
    private Pedido pedido;

    private void imprimirSeparador(String titulo) {
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        imprimirSeparador(testInfo.getDisplayName());
        pedido = new Pedido();
    }

    @Test
    @DisplayName("Deve iniciar em estado Recebido")
    void deveIniciarEmEstadoRecebido() {
        assertEquals("Recebido", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve transicionar de Recebido para Em Preparo")
    void deveTransicionarRecebidoParaEmPreparo() {
        pedido.avancar();
        assertEquals("Em preparo", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve transicionar de Em Preparo para Pronto")
    void deveTransicionarEmPreparoParaPronto() {
        pedido.avancar();
        pedido.avancar();
        assertEquals("Pronto", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve permitir cancelamento de pedido")
    void devePermitirCancelamento() {
        pedido.avancar();
        pedido.cancelar();
        assertEquals("Cancelado", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Pedido cancelado não deve permitir avançar")
    void testPedidoCanceladoNaoPodeAvancar() {
        pedido.cancelar();
        assertThrows(IllegalStateException.class, () -> pedido.avancar());
    }

    @Test
    @DisplayName("Pedido entregue não deve permitir avançar")
    void testPedidoEntregueNaoPodeAvancar() {
        pedido.avancar(); 
        pedido.avancar(); 
        pedido.avancar(); 
        pedido.avancar(); 
        assertThrows(IllegalStateException.class, () -> pedido.avancar());
    }

    @Test
    @DisplayName("Pedido entregue não deve permitir cancelamento")
    void testPedidoEntregueNaoPodeCancelar() {
        pedido.avancar(); 
        pedido.avancar(); 
        pedido.avancar(); 
        pedido.avancar(); 
        assertThrows(IllegalStateException.class, () -> pedido.cancelar());
    }
}
