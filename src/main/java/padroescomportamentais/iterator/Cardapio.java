package padroescomportamentais.iterator;

import padroesestruturais.composite.ItemCardapio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cardapio {
    private final List<ItemCardapio> itens = new ArrayList<>();

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public IteradorCardapio criarIterador() {
        return new IteradorItensCardapio(itens);
    }

    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }
}
