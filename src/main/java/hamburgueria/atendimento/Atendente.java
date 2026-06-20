package hamburgueria.atendimento;

import hamburgueria.pedido.Pedido;

public class Atendente {

    private final CentralPedidos central;

    public Atendente(CentralPedidos central) {
        this.central = central;
    }

    public void receberPedido(Pedido pedido) {
        central.registrarPedido(pedido);
        central.enviarParaCozinha(pedido);
    }
}
