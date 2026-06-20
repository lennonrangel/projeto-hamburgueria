package hamburgueria.pedido.comando;

import java.util.ArrayDeque;
import java.util.Deque;

public class GerenciadorComandosPedido {

    private final Deque<ComandoPedido> historico = new ArrayDeque<>();

    public void executar(ComandoPedido comando) {
        comando.executar();
        historico.push(comando);
    }

    public void desfazerUltimo() {
        if (!historico.isEmpty()) {
            historico.pop().desfazer();
        }
    }

    public int getQuantidadeComandosExecutados() {
        return historico.size();
    }
}
