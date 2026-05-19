package padroescomportamentais.chainofresponsability;

import padroescomportamentais.state.Pedido;

public class SemDesconto extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valorAtual) {
        return valorAtual;
    }
}
