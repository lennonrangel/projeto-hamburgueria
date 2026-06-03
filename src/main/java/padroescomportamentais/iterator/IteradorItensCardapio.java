package padroescomportamentais.iterator;

import padroesestruturais.composite.ItemCardapio;

import java.util.List;

public class IteradorItensCardapio implements IteradorCardapio {
    private final List<ItemCardapio> itens;
    private int posicao;

    public IteradorItensCardapio(List<ItemCardapio> itens) {
        this.itens = itens;
    }

    @Override
    public boolean temProximo() {
        return posicao < itens.size();
    }

    @Override
    public ItemCardapio proximo() {
        if (!temProximo()) {
            return null;
        }
        return itens.get(posicao++);
    }
}
