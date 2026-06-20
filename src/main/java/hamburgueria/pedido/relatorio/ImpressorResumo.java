package hamburgueria.pedido.relatorio;

import hamburgueria.pedido.Pedido;

public class ImpressorResumo implements RelatorioPedido {
    @Override
    public String visitarPedido(Pedido pedido) {
        String resultado = "Resumo do pedido " + pedido.getCodigo() + ": " + pedido.getResumo();
        return resultado;
    }
}
