package br.com.hamburgueria.pedido.estado;

import br.com.hamburgueria.pedido.Pedido;

public interface EstadoPedido {
    void avancar(Pedido pedido);
    void cancelar(Pedido pedido);
    String getNome();
}
