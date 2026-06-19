package hamburgueria.hamburguer;

import hamburgueria.cardapio.MenuItem;

public interface HamburguerPrototype extends MenuItem {
    HamburguerPrototype clonar();
}

