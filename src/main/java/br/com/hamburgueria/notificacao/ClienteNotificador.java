package br.com.hamburgueria.notificacao;

import br.com.hamburgueria.pedido.Pedido;

public class ClienteNotificador implements ObservadorPedido {

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("Cliente avisado: pedido " + pedido.getCodigo() + " esta " + pedido.getEstadoAtual());
    }
}
