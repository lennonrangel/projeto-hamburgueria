package hamburgueria.promocao.condicao;

import hamburgueria.pedido.Pedido;

public class ExpressaoE implements ExpressaoPedido {

    private final ExpressaoPedido esquerda;
    private final ExpressaoPedido direita;

    public ExpressaoE(ExpressaoPedido esquerda, ExpressaoPedido direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean interpretar(Pedido pedido) {
        return esquerda.interpretar(pedido) && direita.interpretar(pedido);
    }
}
