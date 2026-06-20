package hamburgueria.formapagamento.desconto;

import hamburgueria.pedido.Pedido;

public class DescontoPedidoGrande extends DescontoPedido {

    @Override
    protected double calcular(Pedido pedido, double valor) {
        if (valor > 50.0) {
            return valor * 0.9;
        }
        return valor;
    }
}
