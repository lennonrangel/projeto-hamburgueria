package hamburgueria.cardapio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cardapio implements ColecaoCardapio {
    private final List<MenuItem> itens = new ArrayList<>();

    public void adicionarItem(MenuItem item) {
        itens.add(item);
    }

    @Override
    public IteradorCardapio createIterator() {
        return new IteradorItensCardapio(this);
    }

    public List<MenuItem> getItens() {
        return Collections.unmodifiableList(itens);
    }
}

