package padroescomportamentais.command;

import padroescomportamentais.state.Pedido;
import padroesestruturais.composite.ItemCardapio;

public class RemoverItemCommand implements ComandoPedido {

    private final Pedido pedido;
    private final ItemCardapio item;

    public RemoverItemCommand(Pedido pedido, ItemCardapio item) {
        this.pedido = pedido;
        this.item = item;
    }

    @Override
    public void executar() {
        pedido.removerItem(item);
        System.out.println("Item removido do pedido: " + item.getDescricao());
    }

    @Override
    public void desfazer() {
        pedido.adicionarItem(item);
        System.out.println("Remocao desfeita: " + item.getDescricao());
    }
}
