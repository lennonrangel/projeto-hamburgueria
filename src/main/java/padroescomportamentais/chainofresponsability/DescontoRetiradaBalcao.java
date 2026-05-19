package padroescomportamentais.chainofresponsability;

import padroescomportamentais.state.Pedido;

public class DescontoRetiradaBalcao extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valor) {
        if (pedido.isRetiradaBalcao()) {
            System.out.println("Aplicando desconto de 5% para retirada no balcao.");
            return valor * 0.95;
        }
        return valor;
    }
}
