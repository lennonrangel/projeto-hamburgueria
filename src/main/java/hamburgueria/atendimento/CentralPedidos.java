package hamburgueria.atendimento;

import hamburgueria.pedido.Pedido;

public interface CentralPedidos {
    void registrarPedido(Pedido pedido);
    void enviarParaCozinha(Pedido pedido);
    void confirmarPagamento(Pedido pedido, double valorPago);
}
