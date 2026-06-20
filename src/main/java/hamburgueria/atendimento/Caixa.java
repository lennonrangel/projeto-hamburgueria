package hamburgueria.atendimento;

import hamburgueria.pedido.Pedido;

public class Caixa {

    private final CentralPedidos central;

    public Caixa(CentralPedidos central) {
        this.central = central;
    }

    public void finalizarPagamento(Pedido pedido, double valorPago) {
        central.confirmarPagamento(pedido, valorPago);
    }
}
