package br.com.hamburgueria.atendimento;

import br.com.hamburgueria.pedido.Pedido;

public interface CentralPedidosMediator {
    void registrarPedido(Pedido pedido);
    void enviarParaCozinha(Pedido pedido);
    void confirmarPagamento(Pedido pedido, double valorPago);
}
