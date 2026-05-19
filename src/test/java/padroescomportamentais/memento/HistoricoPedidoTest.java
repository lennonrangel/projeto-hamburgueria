package padroescomportamentais.memento;

import padroescomportamentais.state.Pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Padrão Memento - Histórico do Pedido")
class HistoricoPedidoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [MEMENTO] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve salvar e restaurar estado do pedido")
    void deveSalvarERestaurarEstado() {
        imprimirSeparador("Backup e Restauração de Estado");
        System.out.println();
        
        Pedido pedido = new Pedido();
        HistoricoPedido historicoPedido = new HistoricoPedido();

        System.out.println("Estado inicial: " + pedido.getEstadoAtual());
        assertEquals("Recebido", pedido.getEstadoAtual());
        
        System.out.println("Salvando snapshot do estado atual...");
        historicoPedido.adicionarSnapshot(new RegistroPedido(pedido.getEstado()));

        pedido.avancar();
        System.out.println("Estado após avançar: " + pedido.getEstadoAtual());
        assertEquals("Em preparo", pedido.getEstadoAtual());

        System.out.println("Restaurando estado a partir do histórico...");
        pedido.restaurarEstado(historicoPedido.getUltimo().getEstado());
        System.out.println("Estado após restauração: " + pedido.getEstadoAtual());
        
        assertEquals("Recebido", pedido.getEstadoAtual());
    }
}
