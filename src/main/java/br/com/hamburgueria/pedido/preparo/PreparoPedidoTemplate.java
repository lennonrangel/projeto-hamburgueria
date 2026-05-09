package br.com.hamburgueria.pedido.preparo;

import br.com.hamburgueria.pedido.Pedido;

public abstract class PreparoPedidoTemplate {

    public final void preparar(Pedido pedido) {
        separarIngredientes(pedido);
        prepararLanche(pedido);
        embalarPedido(pedido);
        finalizar(pedido);
    }

    protected void separarIngredientes(Pedido pedido) {
        System.out.println("Separando ingredientes do pedido " + pedido.getCodigo() + ".");
    }

    protected abstract void prepararLanche(Pedido pedido);

    protected void embalarPedido(Pedido pedido) {
        System.out.println("Embalando pedido " + pedido.getCodigo() + ".");
    }

    protected void finalizar(Pedido pedido) {
        if ("Em preparo".equals(pedido.getEstadoAtual())) {
            pedido.avancar();
        }
    }
}
