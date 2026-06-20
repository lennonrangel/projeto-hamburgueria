package hamburgueria.formapagamento.desconto;

import hamburgueria.pedido.Pedido;

public class DescontoRetiradaBalcao extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valor) {
        if (pedido.isRetiradaBalcao()) {
            return valor * 0.95;
        }
        return valor;
    }
}
