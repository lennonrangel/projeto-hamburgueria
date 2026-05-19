package padroescomportamentais.mediator;

import padroescomportamentais.state.Pedido;

public class Atendente {

    private final CentralPedidos central;

    public Atendente(CentralPedidos central) {
        this.central = central;
    }

    public void receberPedido(Pedido pedido) {
        System.out.println("Atendente recebendo pedido " + pedido.getCodigo());
        central.registrarPedido(pedido);
        central.enviarParaCozinha(pedido);
    }
}
