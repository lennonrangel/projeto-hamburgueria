package br.com.hamburgueria.pedido.estado;

import br.com.hamburgueria.pedido.Pedido;

public class PedidoRecebido implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new PedidoEmPreparo());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
    }

    @Override
    public String getNome() {
        return "Recebido";
    }
}
