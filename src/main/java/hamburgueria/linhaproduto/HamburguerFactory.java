package hamburgueria.linhaproduto;

import hamburgueria.cardapio.MenuItem;

public interface HamburguerFactory {
    MenuItem criarHamburguerPrincipal();
    MenuItem criarHamburguerEspecial();
    String getNomeLinha();
}

