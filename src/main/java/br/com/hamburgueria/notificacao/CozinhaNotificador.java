package br.com.hamburgueria.notificacao;

import br.com.hamburgueria.pedido.Pedido;

public class CozinhaNotificador implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("Cozinha atualizada sobre o pedido " + pedido.getCodigo() + ": " + pedido.getEstadoAtual());
    }
}
