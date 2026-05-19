package padroescomportamentais.mediator;

import padroescomportamentais.state.Pedido;

public class Caixa {

    private final CentralPedidos central;

    public Caixa(CentralPedidos central) {
        this.central = central;
    }

    public void finalizarPagamento(Pedido pedido, double valorPago) {
        System.out.println("Caixa processando pagamento do pedido " + pedido.getCodigo());
        central.confirmarPagamento(pedido, valorPago);
    }
}
