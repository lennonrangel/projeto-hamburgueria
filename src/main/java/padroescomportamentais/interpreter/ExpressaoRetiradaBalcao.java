package padroescomportamentais.interpreter;

import padroescomportamentais.state.Pedido;

public class ExpressaoRetiradaBalcao implements ExpressaoPedido {

    @Override
    public boolean interpretar(Pedido pedido) {
        return pedido.isRetiradaBalcao();
    }
}
