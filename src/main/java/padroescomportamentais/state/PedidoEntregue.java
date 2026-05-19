package padroescomportamentais.state;

public class PedidoEntregue implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi entregue.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Pedido entregue não pode ser cancelado.");
    }

    @Override
    public String getNome() {
        return "Entregue";
    }
}
