package br.com.hamburgueria.atendimento;

import br.com.hamburgueria.pedido.Pedido;

public class Atendente {

    private final CentralPedidosMediator central;

    public Atendente(CentralPedidosMediator central) {
        this.central = central;
    }

    public void receberPedido(Pedido pedido) {
        central.registrarPedido(pedido);
        central.enviarParaCozinha(pedido);
    }
}
