package padroescomportamentais.observer;

import padroescomportamentais.state.Pedido;

public interface MonitorPedido {
    void atualizar(Pedido pedido);
}
