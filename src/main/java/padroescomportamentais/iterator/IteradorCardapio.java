package padroescomportamentais.iterator;

import padroesestruturais.composite.ItemCardapio;

public interface IteradorCardapio {
    boolean temProximo();
    ItemCardapio proximo();
}
