package padroescomportamentais.visitor;

import padroescomportamentais.state.Pedido;

public class ImpressorResumo implements RelatorioPedido {
    @Override
    public String visitarPedido(Pedido pedido) {
        String resultado = "Resumo do pedido " + pedido.getCodigo() + ": " + pedido.getResumo();
        System.out.println(resultado);
        return resultado;
    }
}
