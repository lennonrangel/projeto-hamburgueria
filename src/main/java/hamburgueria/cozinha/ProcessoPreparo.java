package hamburgueria.cozinha;

import hamburgueria.pedido.Pedido;

public abstract class ProcessoPreparo {

    public final void preparar(Pedido pedido) {
        separarIngredientes(pedido);
        prepararHamburguer(pedido);
        embalarPedido(pedido);
        finalizar(pedido);
    }

    protected void separarIngredientes(Pedido pedido) {
    }

    protected abstract void prepararHamburguer(Pedido pedido);

    protected void embalarPedido(Pedido pedido) {
    }

    protected void finalizar(Pedido pedido) {
        if ("Em preparo".equals(pedido.getEstadoAtual())) {
            pedido.avancar();
        }
    }
}
