package br.com.hamburgueria.cardapio;

import br.com.hamburgueria.cardapio.ingredientes.proteinas.Proteina;

public class Lanche implements ItemCardapio {

    private final String nome;
    private final String tipoPao;
    private final Proteina proteina;
    private final double precoBase;

    public Lanche(String nome, String tipoPao, Proteina proteina, double precoBase) {
        this.nome = nome;
        this.tipoPao = tipoPao;
        this.proteina = proteina;
        this.precoBase = precoBase;
    }

    @Override
    public String getDescricao() {
        return nome + " com pão " + tipoPao + ", " + proteina.getDescricao();
    }

    @Override
    public double getPreco() {
        return precoBase + proteina.getPreco();
    }

    public String getNome() {
        return nome;
    }

    public String getTipoPao() {
        return tipoPao;
    }

    public Proteina getProteina() {
        return proteina;
    }
}
