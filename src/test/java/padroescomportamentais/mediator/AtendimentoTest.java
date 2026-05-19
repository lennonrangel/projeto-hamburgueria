package padroescomportamentais.mediator;

import padroescomportamentais.state.Pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Mediator - Central de Hamburgueria")
class AtendimentoTest {
    private CentralHamburgueria central;
    private Atendente atendente1;
    private Atendente atendente2;

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [MEDIATOR] " + titulo);
        System.out.println("========================================================");
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
        System.out.println();
        setup();
        Pedido pedido = new Pedido();
        atendente1.receberPedido(pedido);
        System.out.println("Pedido " + pedido.getCodigo() + " registrado com sucesso.");
        assertEquals(1, central.getPedidos().size());
        assertTrue(central.getPedidos().contains(pedido));
    }

    @Test
    @DisplayName("Deve mudar estado do pedido via mediator")
    void deveMudarEstadoPedidoViaMediator() {
        imprimirSeparador("Mudança de Estado Coordenada");
        System.out.println();
        setup();
        Pedido pedido = new Pedido();
        atendente1.receberPedido(pedido);
        System.out.println("Estado atual do pedido: " + pedido.getEstadoAtual());
        assertEquals("Em preparo", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve coordenar múltiplos atendentes")
    void deveCoordenarMultiplosAtendentes() {
        imprimirSeparador("Coordenação de Múltiplos Atendentes");
        System.out.println();
        setup();
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        atendente1.receberPedido(pedido1);
        atendente2.receberPedido(pedido2);
        System.out.println("Total de pedidos na central: " + central.getPedidos().size());
        assertEquals(2, central.getPedidos().size());
    }
}
