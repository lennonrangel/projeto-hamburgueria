package padroescriacao.builder;

import padroesestruturais.composite.ItemCardapio;
import padroesestruturais.bridge.Proteina;

public class ReceitaHamburguer {

    public ItemCardapio montarHamburguer(String nome, String pao, Proteina proteina, double precoBase) {
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
