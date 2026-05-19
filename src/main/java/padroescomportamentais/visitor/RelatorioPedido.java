package padroescomportamentais.visitor;

import padroescomportamentais.state.Pedido;

public interface RelatorioPedido {
    String visitarPedido(Pedido pedido);
}
