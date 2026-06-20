package hamburgueria.pedido.comando;

import hamburgueria.pedido.Pedido;

import hamburgueria.cardapio.MenuItem;

public class RemoverItemCommand implements ComandoPedido {

    private final Pedido pedido;
    private final MenuItem item;

    public RemoverItemCommand(Pedido pedido, MenuItem item) {
        this.pedido = pedido;
        this.item = item;
    }

    @Override
    public void executar() {
        pedido.removerItem(item);
    }

    @Override
    public void desfazer() {
        pedido.adicionarItem(item);
    }
}

