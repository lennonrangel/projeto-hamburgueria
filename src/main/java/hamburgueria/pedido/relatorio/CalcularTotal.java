package hamburgueria.pedido.relatorio;

import hamburgueria.pedido.Pedido;

public class CalcularTotal implements RelatorioPedido {
    @Override
    public String visitarPedido(Pedido pedido) {
        String resultado = "Total do pedido " + pedido.getCodigo() + ": R$ " + String.format("%.2f", pedido.calcularTotal());
        return resultado;
    }
}
