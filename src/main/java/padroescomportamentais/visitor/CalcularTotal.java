package padroescomportamentais.visitor;

import padroescomportamentais.state.Pedido;

public class CalcularTotal implements RelatorioPedido {
    @Override
    public String visitarPedido(Pedido pedido) {
        String resultado = "Total do pedido " + pedido.getCodigo() + ": R$ " + String.format("%.2f", pedido.calcularTotal());
        System.out.println(resultado);
        return resultado;
    }
}
