package br.com.hamburgueria.pedido.estado;

import br.com.hamburgueria.pedido.Pedido;

public class PedidoPronto implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new PedidoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Pedido pronto nao pode ser cancelado.");
    }

    @Override
    public String getNome() {
        return "Pronto";
    }
}
