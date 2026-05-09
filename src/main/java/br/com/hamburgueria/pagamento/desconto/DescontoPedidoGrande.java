package br.com.hamburgueria.pagamento.desconto;

import br.com.hamburgueria.pedido.Pedido;

public class DescontoPedidoGrande extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valorAtual) {
        if (pedido.calcularTotal() >= 80.0) {
            return valorAtual * 0.95;
        }

        return valorAtual;
    }
}
