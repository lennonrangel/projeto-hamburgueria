package padroescomportamentais.chainofresponsability;

import padroescomportamentais.state.Pedido;

public class DescontoPedidoGrande extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valor) {
        if (valor > 50.0) {
            System.out.println("Aplicando desconto de 10% para pedido grande.");
            return valor * 0.9;
        }
        return valor;
    }
}
