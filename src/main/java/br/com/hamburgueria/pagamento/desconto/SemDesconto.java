package br.com.hamburgueria.pagamento.desconto;

import br.com.hamburgueria.pedido.Pedido;

public class SemDesconto extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valorAtual) {
        return valorAtual;
    }
}
