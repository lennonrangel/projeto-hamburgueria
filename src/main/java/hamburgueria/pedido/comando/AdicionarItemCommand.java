package hamburgueria.pedido.comando;

import hamburgueria.pedido.Pedido;

import hamburgueria.cardapio.MenuItem;

public class AdicionarItemCommand implements ComandoPedido {

    private final Pedido pedido;
    private final MenuItem item;

    public AdicionarItemCommand(Pedido pedido, MenuItem item) {
        this.pedido = pedido;
        this.item = item;
    }

    @Override
    public void executar() {
        pedido.adicionarItem(item);
    }

    @Override
    public void desfazer() {
        pedido.removerItem(item);
    }
}

