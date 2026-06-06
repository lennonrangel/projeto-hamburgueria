package padroescomportamentais.interpreter;

import padroescomportamentais.state.Pedido;

public interface ExpressaoPedido {
    boolean interpretar(Pedido pedido);
}
