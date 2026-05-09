package br.com.hamburgueria.pedido.estado;

import br.com.hamburgueria.pedido.Pedido;

public class PedidoEmRota implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new PedidoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoDevolucao());
    }

    @Override
    public String getNome() {
        return "Em rota de entrega";
    }
}
