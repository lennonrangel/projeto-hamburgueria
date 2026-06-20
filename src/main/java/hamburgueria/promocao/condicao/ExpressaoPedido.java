package hamburgueria.promocao.condicao;

import hamburgueria.pedido.Pedido;

public interface ExpressaoPedido {
    boolean interpretar(Pedido pedido);
}
