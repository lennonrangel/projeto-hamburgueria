package padroescomportamentais.observer;

import padroescomportamentais.state.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("Teste do Padrão Observer - Notificações")
class NotificacaoTest {
    private Pedido pedido;

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [OBSERVER] " + titulo);
        System.out.println("========================================================");
        System.out.println();
    }

    @BeforeEach

    void setup(TestInfo testInfo) {
        imprimirSeparador(testInfo.getDisplayName());
        pedido = new Pedido();
    }

    @Test
    @DisplayName("Deve registrar observador de pedido")
    void deveRegistrarObservador() {
        final int[] notificacoes = {0};
        MonitorPedido observador = p -> notificacoes[0]++;
        pedido.adicionarObservador(observador);
        pedido.avancar();
        assertEquals(1, notificacoes[0]);
    }

    @Test
    @DisplayName("Deve notificar múltiplos observadores")
    void deveNotificarMultiplosObservadores() {
        final int[] notificacoes1 = {0};
        final int[] notificacoes2 = {0};
        MonitorPedido obs1 = p -> notificacoes1[0]++;
        MonitorPedido obs2 = p -> notificacoes2[0]++;
        pedido.adicionarObservador(obs1);
        pedido.adicionarObservador(obs2);
        pedido.avancar();
        assertEquals(1, notificacoes1[0]);
        assertEquals(1, notificacoes2[0]);
    }

    @Test
    @DisplayName("Deve manter referência ao pedido em notificação")
    void deveManterReferenciaPedidoEmNotificacao() {
        final Pedido[] pedidoRecebido = {null};
        MonitorPedido observador = p -> pedidoRecebido[0] = p;
        pedido.adicionarObservador(observador);
        pedido.avancar();
        assertSame(pedido, pedidoRecebido[0]);
    }
}
