package hamburgueria.hamburguer;

import hamburgueria.hamburguer.proteina.Proteina;

import hamburgueria.cardapio.MenuItem;

public class ReceitaHamburguer {

    public MenuItem montarHamburguer(String nome, String pao, Proteina proteina, double precoBase) {
        return new ChefeCozinha()
                .comNome(nome)
                .comPao(pao)
                .comProteina(proteina)
                .comPrecoBase(precoBase)
                .comQueijo("cheddar")
                .comBacon()
                .comMolho()
                .montar();
    }
}

