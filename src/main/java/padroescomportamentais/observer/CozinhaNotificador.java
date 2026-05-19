package padroescomportamentais.observer;

import padroescomportamentais.state.Pedido;

public class CozinhaNotificador implements MonitorPedido {

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("Cozinha atualizada sobre o pedido " + pedido.getCodigo() + ": " + pedido.getEstadoAtual());
    }
}
