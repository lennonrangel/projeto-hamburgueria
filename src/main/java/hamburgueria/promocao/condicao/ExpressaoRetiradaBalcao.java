package hamburgueria.promocao.condicao;

import hamburgueria.pedido.Pedido;

public class ExpressaoRetiradaBalcao implements ExpressaoPedido {

    @Override
    public boolean interpretar(Pedido pedido) {
        return pedido.isRetiradaBalcao();
    }
}
