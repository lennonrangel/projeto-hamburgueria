package padroescomportamentais.templatemethod;

import padroescomportamentais.state.Pedido;

public abstract class ProcessoPreparo {

    public final void preparar(Pedido pedido) {
        separarIngredientes(pedido);
        prepararHamburguer(pedido);
        embalarPedido(pedido);
        finalizar(pedido);
    }

    protected void separarIngredientes(Pedido pedido) {
        System.out.println("Separando ingredientes do pedido " + pedido.getCodigo() + ".");
    }

    protected abstract void prepararHamburguer(Pedido pedido);

    protected void embalarPedido(Pedido pedido) {
        System.out.println("Embalando pedido " + pedido.getCodigo() + ".");
    }

    protected void finalizar(Pedido pedido) {
        if ("Em preparo".equals(pedido.getEstadoAtual())) {
            pedido.avancar();
        }
    }
}
