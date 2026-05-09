package br.com.hamburgueria.pagamento.desconto;

import br.com.hamburgueria.pedido.Pedido;

public class DescontoRetiradaBalcao extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valorAtual) {
        if (pedido.isRetiradaBalcao()) {
            return valorAtual * 0.97;
        }

        return valorAtual;
    }
}
