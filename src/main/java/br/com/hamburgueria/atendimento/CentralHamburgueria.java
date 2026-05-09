package br.com.hamburgueria.atendimento;

import br.com.hamburgueria.pedido.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CentralHamburgueria implements CentralPedidosMediator {

    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido " + pedido.getCodigo() + " registrado na central.");
    }

    @Override
    public void enviarParaCozinha(Pedido pedido) {
        System.out.println("Central enviou o pedido " + pedido.getCodigo() + " para a cozinha.");
        pedido.avancar();
    }

    @Override
    public void confirmarPagamento(Pedido pedido, double valorPago) {
        System.out.println("Pagamento do pedido " + pedido.getCodigo() + " confirmado: R$ " + String.format("%.2f", valorPago));
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }
}
