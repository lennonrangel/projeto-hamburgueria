package padroescomportamentais.command;

import padroescomportamentais.state.Pedido;
import padroesestruturais.composite.ItemCardapio;

public class AdicionarItemCommand implements ComandoPedido {

    private final Pedido pedido;
    private final ItemCardapio item;

    public AdicionarItemCommand(Pedido pedido, ItemCardapio item) {
        this.pedido = pedido;
        this.item = item;
    }

    @Override
    public void executar() {
        pedido.adicionarItem(item);
        System.out.println("Item adicionado ao pedido: " + item.getDescricao());
    }

    @Override
    public void desfazer() {
        pedido.removerItem(item);
        System.out.println("Adicao desfeita: " + item.getDescricao());
    }
}
