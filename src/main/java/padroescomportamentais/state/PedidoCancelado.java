package padroescomportamentais.state;

public class PedidoCancelado implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        throw new IllegalStateException("Pedido cancelado não pode avançar.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        // Já está cancelado
    }

    @Override
    public String getNome() {
        return "Cancelado";
    }
}
