package hamburgueria.atendimento;

import hamburgueria.pedido.Pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Mediator - Central de Hamburgueria")
class AtendimentoTest {
    private CentralHamburgueria central;
    private Atendente atendente1;
    private Atendente atendente2;

    private void imprimirSeparador(String titulo) {
    }

    private void setup() {
        central = new CentralHamburgueria();
        atendente1 = new Atendente(central);
        atendente2 = new Atendente(central);
    }

    @Test
    @DisplayName("Deve registrar pedido através do mediator")
    void deveRegistrarPedidoThroughMediator() {
        imprimirSeparador("Registro de Pedido via Mediador");
        setup();
        Pedido pedido = new Pedido();
        atendente1.receberPedido(pedido);
        assertEquals(1, central.getPedidos().size());
        assertTrue(central.getPedidos().contains(pedido));
    }

    @Test
    @DisplayName("Deve mudar estado do pedido via mediator")
    void deveMudarEstadoPedidoViaMediator() {
        imprimirSeparador("Mudança de Estado Coordenada");
        setup();
        Pedido pedido = new Pedido();
        atendente1.receberPedido(pedido);
        assertEquals("Em preparo", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve coordenar múltiplos atendentes")
    void deveCoordenarMultiplosAtendentes() {
        imprimirSeparador("Coordenação de Múltiplos Atendentes");
        setup();
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        atendente1.receberPedido(pedido1);
        atendente2.receberPedido(pedido2);
        assertEquals(2, central.getPedidos().size());
    }

    @Test
    @DisplayName("Deve permitir finalizar pagamento via Caixa")
    void testCaixaFinalizarPagamentoDisparaCentral() {
        setup();
        Pedido pedido = new Pedido();
        Caixa caixa = new Caixa(central);
        assertDoesNotThrow(() -> caixa.finalizarPagamento(pedido, 50.0));
    }

    @Test
    @DisplayName("Lista de pedidos na central deve ser imutavel")
    void testCentralPedidosListaImutavel() {
        setup();
        assertThrows(UnsupportedOperationException.class, () -> {
            central.getPedidos().add(new Pedido());
        });
    }
}
