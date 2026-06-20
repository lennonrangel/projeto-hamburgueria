package hamburgueria.notificacao;

import hamburgueria.pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Observer - Notificações")
class NotificacaoTest {
    private Pedido pedido;

    private void imprimirSeparador(String titulo) {
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

    @Test
    @DisplayName("Deve notificar observadores ao cancelar pedido")
    void deveNotificarObservadoresAoCancelarPedido() {
        final int[] notificacoes = {0};
        MonitorPedido observador = p -> notificacoes[0]++;
        pedido.adicionarObservador(observador);
        pedido.cancelar();
        assertEquals(1, notificacoes[0]);
    }

    @Test
    @DisplayName("Deve executar notificadores concretos sem erro")
    void deveExecutarNotificadoresConcretos() {
        MonitorPedido clienteObs = new ClienteNotificador();
        MonitorPedido cozinhaObs = new CozinhaNotificador();

        pedido.adicionarObservador(clienteObs);
        pedido.adicionarObservador(cozinhaObs);

        assertDoesNotThrow(() -> pedido.avancar());
    }
}
