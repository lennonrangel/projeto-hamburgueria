package hamburgueria.pedido.historico;

import hamburgueria.pedido.Pedido;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Memento - Histórico do Pedido")
class HistoricoPedidoTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve salvar e restaurar estado do pedido")
    void deveSalvarERestaurarEstado() {
        imprimirSeparador("Backup e Restauração de Estado");
        
        Pedido pedido = new Pedido();
        HistoricoPedido historicoPedido = new HistoricoPedido();

        assertEquals("Recebido", pedido.getEstadoAtual());
        
        historicoPedido.adicionarSnapshot(new RegistroPedido(pedido.getEstado()));

        pedido.avancar();
        assertEquals("Em preparo", pedido.getEstadoAtual());

        pedido.restaurarEstado(historicoPedido.getUltimo().getEstado());
        
        assertEquals("Recebido", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve salvar e restaurar estado usando a API do Originator (salvarEstado/restaurar)")
    void deveSalvarERestaurarEstadoViaOriginator() {
        imprimirSeparador("Checkpoint via Pedido.salvarEstado() / Pedido.restaurar()");

        Pedido pedido = new Pedido();
        HistoricoPedido historicoPedido = new HistoricoPedido();

        assertEquals("Recebido", pedido.getEstadoAtual());
        historicoPedido.adicionarSnapshot(pedido.salvarEstado());

        pedido.avancar();
        pedido.avancar();
        assertEquals("Pronto", pedido.getEstadoAtual());

        pedido.restaurar(historicoPedido.getUltimo());
        assertEquals("Recebido", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve retornar nulo ao buscar ultimo snapshot em historico vazio")
    void testHistoricoVazioUltimoRetornaNull() {
        HistoricoPedido historico = new HistoricoPedido();
        assertNull(historico.getUltimo());
    }

    @Test
    @DisplayName("Deve permitir chamar removerUltimo em historico vazio sem falhar")
    void testHistoricoRemoverUltimoEmHistoricoVazio() {
        HistoricoPedido historico = new HistoricoPedido();
        assertDoesNotThrow(() -> historico.removerUltimo());
    }

    @Test
    @DisplayName("Deve suportar multiplos checkpoints e restaurar passo a passo")
    void testHistoricoVariosSnapshots() {
        Pedido pedido = new Pedido();
        HistoricoPedido historico = new HistoricoPedido();

        // 1. Recebido
        historico.adicionarSnapshot(pedido.salvarEstado());

        // 2. Em preparo
        pedido.avancar();
        historico.adicionarSnapshot(pedido.salvarEstado());

        // 3. Pronto
        pedido.avancar();
        assertEquals("Pronto", pedido.getEstadoAtual());

        // Restaura para Em preparo
        pedido.restaurar(historico.getUltimo());
        assertEquals("Em preparo", pedido.getEstadoAtual());

        // Remove o ultimo (Em preparo) e restaura para o anterior (Recebido)
        historico.removerUltimo();
        pedido.restaurar(historico.getUltimo());
        assertEquals("Recebido", pedido.getEstadoAtual());
    }
}
