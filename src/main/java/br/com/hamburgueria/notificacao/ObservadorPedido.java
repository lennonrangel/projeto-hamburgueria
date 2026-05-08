package br.com.hamburgueria.notificacao;

import br.com.hamburgueria.pedido.Pedido;

public interface ObservadorPedido {
    void atualizar(Pedido pedido);
}
