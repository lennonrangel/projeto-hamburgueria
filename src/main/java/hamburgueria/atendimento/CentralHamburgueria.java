package hamburgueria.atendimento;

import hamburgueria.pedido.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CentralHamburgueria implements CentralPedidos {

    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public void enviarParaCozinha(Pedido pedido) {
        pedido.avancar();
    }

    @Override
    public void confirmarPagamento(Pedido pedido, double valorPago) {
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }
}
