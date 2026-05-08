package br.com.hamburgueria.pedido.estado;

import br.com.hamburgueria.pedido.Pedido;

public class PedidoEmPreparo implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new PedidoPronto());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
    }

    @Override
    public String getNome() {
        return "Em preparo";
    }
}
