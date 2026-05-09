package br.com.hamburgueria.atendimento;

import br.com.hamburgueria.pedido.Pedido;

public class Caixa {

    private final CentralPedidosMediator central;

    public Caixa(CentralPedidosMediator central) {
        this.central = central;
    }

    public void finalizarPagamento(Pedido pedido, double valorPago) {
        central.confirmarPagamento(pedido, valorPago);
    }
}
