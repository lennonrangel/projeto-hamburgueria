package hamburgueria.formapagamento.desconto;

import hamburgueria.pedido.Pedido;

public class SemDesconto extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valorAtual) {
        return valorAtual;
    }
}
