package br.com.hamburgueria.pedido.estado;

import br.com.hamburgueria.pedido.Pedido;

public class PedidoCancelado implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        throw new IllegalStateException("Pedido cancelado nao pode avancar.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Pedido ja esta cancelado.");
    }

    @Override
    public String getNome() {
        return "Cancelado";
    }
}
