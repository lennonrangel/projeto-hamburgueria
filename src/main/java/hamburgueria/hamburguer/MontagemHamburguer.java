package hamburgueria.hamburguer;

import hamburgueria.hamburguer.proteina.Proteina;

import hamburgueria.cardapio.MenuItem;

public interface MontagemHamburguer {
    MontagemHamburguer comNome(String nome);
    MontagemHamburguer comPao(String tipoPao);
    MontagemHamburguer comQueijo(String tipoQueijo);
    MontagemHamburguer comProteina(Proteina proteina);
    MontagemHamburguer comPrecoBase(double precoBase);
    MontagemHamburguer comBacon();
    MontagemHamburguer comMolho();
    MontagemHamburguer comSalada();
    MontagemHamburguer comOnionRings();
    MontagemHamburguer comPicles();
    MenuItem montar();
}

